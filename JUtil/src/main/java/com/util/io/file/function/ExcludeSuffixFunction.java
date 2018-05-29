package com.util.io.file.function;


import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * 去除文件后缀
 *
 * @author wulang
 * @version v1.0
 * @date 2017年12月01日 14:03
 * @description
 * @modified By:
 * @modifued reason:
 */
public class ExcludeSuffixFunction implements Function<String, String> {
    /**
     * 后缀标识
     */
    private static final String IDENTIFIER = ".";
    /**
     * 对象实例
     */
    private static volatile ExcludeSuffixFunction INSTANCE;

    /**
     * 获取实例
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/12/1 14:07
     * @modify by user: {修改人}  2017/12/1 14:07
     * @modify by reason:
     */
    public static ExcludeSuffixFunction getInstance() {
        if (null == INSTANCE) {
            synchronized (ExcludeSuffixFunction.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ExcludeSuffixFunction();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String apply(String input) {
        String name = "";
        if (StringUtils.isNotBlank(input)) {
            String suffix = input.substring(input.lastIndexOf(IDENTIFIER));
            int len = StringUtils.isNotBlank(suffix) ? suffix.length() : 0;
            name = input.substring(0, input.length() - len);
        }
        return name;
    }
}
