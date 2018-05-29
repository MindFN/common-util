package com.util.math.comparator;

import com.util.common.CNStringUtil;
import com.util.math.constant.SortConstant;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Objects;

/**
 * 字段名称进行排序
 * 排序的优先级为 特殊符号>数字>小写字母>大写字母>中文拼音首字母
 * 同类型按照Unicode编码升序排序
 *
 * @Author: wulang
 * @Date: 2017年10月13日 15:07
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public abstract class BaseNameComparator<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        int num1 = Objects.isNull(o1) ? 0 : 1;
        int num2 = Objects.isNull(o2) ? 0 : 1;
        int result = num1 - num2;
        switch (result) {
            case SortConstant.LESS:
                result = SortConstant.LESS;
                break;
            case SortConstant.GREATER:
                result = SortConstant.GREATER;
                break;
            default:
                if (!Objects.isNull(o1)) {
                    result = compareDetail(fetchCompareField(o1), fetchCompareField(o2));
                } else {
                    result = SortConstant.EQUAL;
                }
                break;
        }
        return result;
    }

    /**
     * 获取需要比较的值
     *
     * @param target
     * @return
     * @author: wulang
     * @date: 2017/11/7 11:33
     * @modify by user: {修改人}  2017/11/7 11:33
     * @modify by reason:
     */
    protected abstract String fetchCompareField(T target);

    /**
     * 比较的细节
     *
     * @param o1
     * @param o2
     * @return
     * @author: wulang
     * @date: 2017/10/13 16:59
     * @modify by user: {修改人}  2017/10/13 16:59
     * @modify by reason:
     */
    private int compareDetail(String o1, String o2) {
        //确保取到的值不为空
        int num1 = StringUtils.isBlank(o1) ? 0 : 1;
        int num2 = StringUtils.isBlank(o2) ? 0 : 1;
        int result = num1 - num2;
        if (SortConstant.EQUAL == result && StringUtils.isNotBlank(o1)) {
            int[] codeArr1 = wrapStringCode(o1);
            int[] codeArr2 = wrapStringCode(o2);
            for (int i = 0; i < codeArr1.length && i < codeArr2.length; i++) {
                result = codeArr1[i] - codeArr2[i];
                if (SortConstant.EQUAL != result) {
                    result = result < 0 ? SortConstant.LESS : SortConstant.GREATER;
                    break;
                }
            }
        }
        if (result != SortConstant.EQUAL) {
            result = result > SortConstant.EQUAL ? SortConstant.GREATER : SortConstant.LESS;
        }
        return result;

    }

    /**
     * 将字符串转换为code
     *
     * @param str
     * @return
     * @author: wulang
     * @date: 2017/10/13 16:58
     * @modify by user: {修改人}  2017/10/13 16:58
     * @modify by reason:
     */
    private int[] wrapStringCode(String str) {
        char[] originStrArr = str.toCharArray();
        int[] codeArr = new int[originStrArr.length];
        int index = -1;
        for (char ch : originStrArr) {
            codeArr[++index] = wrapeCode(ch);
        }
        return codeArr;
    }

    /**
     * 根据字符获取其code,
     * 其中不同类型对应的code大小
     * 中文>大写字母>小写字母>数字>特殊符号
     * 特殊符号
     *
     * @param ch
     * @return
     * @author: wulang
     * @date: 2017/10/13 16:56
     * @modify by user: {修改人}  2017/10/13 16:56
     * @modify by reason:
     */
    private int wrapeCode(char ch) {
        int code = 0;
        if (CNStringUtil.isChineseCharacter(ch)) {
            ch = PinyinHelper.toHanyuPinyinStringArray(ch)[0].charAt(0);
            ch = (char) (ch - 'a' + 'A');
            code = ch + 1 << 20;
        } else if (CNStringUtil.UPPER_CASE_ALPHABET_SET.contains(ch)) {
            code = ch + 1 << 19;
        } else if (CNStringUtil.LOWER_CASE_ALPHABET_SET.contains(ch)) {
            code = ch + 1 << 18;
        } else if (CNStringUtil.DIGIT_SET.contains(ch)) {
            code = ch + 1 << 17;
        } else {
            code = ch + 1 << 16;
        }
        return code;
    }

}
