package com.util.common;

import com.google.common.collect.Sets;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Arrays;
import java.util.Set;

/**
 * 中文字符工具
 *
 * @Author: wulang
 * @Date: 2017年08月16日 15:49
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class CNStringUtil {
    /**
     * Unicode中文起始编码
     */
    public static final char CH_START_INDEX = 0x4E00;
    /**
     * Unicode中文终止编码
     */
    public static final char CH_END_INDEX = 0x9FA5;
    public static final char EXTRA_CHAR = 3007;


    /**
     * 数字字符
     */
    public static final Set<Character> DIGIT_SET = getDigitSet();

    /**
     * 小写字母
     */
    public static final Set<Character> LOWER_CASE_ALPHABET_SET = getLowerCaseAlphabetSet();
    /**
     * 大写字母
     */
    public static final Set<Character> UPPER_CASE_ALPHABET_SET = getUpperCaseAlphabetSet();

    /**
     * 获得小写字母集合
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:22
     * @modify by user: {修改人}  2017/12/1 10:22
     * @modify by reason:
     */
    private static Set<Character> getLowerCaseAlphabetSet() {
        Character[] chArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return Sets.newHashSet(Arrays.asList(chArr));
    }

    /**
     * 获得大写字母集合
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:23
     * @modify by user: {修改人}  2017/12/1 10:23
     * @modify by reason:
     */
    private static Set<Character> getUpperCaseAlphabetSet() {
        Character[] chArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return Sets.newHashSet(Arrays.asList(chArr));
    }

    /**
     * 获得数字集合
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:23
     * @modify by user: {修改人}  2017/12/1 10:23
     * @modify by reason:
     */
    private static Set<Character> getDigitSet() {
        Character[] digitArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return Sets.newHashSet(Arrays.asList(digitArr));
    }

    /**
     * 获取字符串中中文首字母
     *
     * @param srcStr
     * @return
     * @author: wulang
     * @date: 2017/8/16 16:50
     * @modify by user: {修改人}  2017/8/16 16:50
     * @modify by reason:
     */
    public static String fetchFirstCharacterFromString(String srcStr) {
        char[] originStrArr = srcStr.toCharArray();
        char[] charArr = new char[originStrArr.length];
        int index = -1;
        for (char ch : originStrArr) {
            if (isChineseCharacter(ch)) {
                ch = PinyinHelper.toHanyuPinyinStringArray(ch)[0].charAt(0);
                ch = (char) (ch - 'a' + 'A');
            }
            charArr[++index] = ch;
        }
        return new String(charArr);
    }

    /**
     * 判断是否为中文字符
     *
     * @param src
     * @return
     * @author: wulang
     * @date: 2017/8/16 16:51
     * @modify by user: {修改人}  2017/8/16 16:51
     * @modify by reason:
     */
    public static boolean isChineseCharacter(char src) {
        return src == EXTRA_CHAR || (src <= CH_END_INDEX && src >= CH_START_INDEX);
    }
}
