package com.util.common;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年12月01日 10:53
 * @description
 * @modified By:
 * @modifued reason:
 */
public class RegUtil {
    /**
     * 获得matcher
     *
     * @param source
     * @param regEx
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:02
     * @modify by user: {修改人}  2017/12/1 11:02
     * @modify by reason:
     */
    private static Matcher getMatcher(String source, String regEx) {
        Matcher matcher = null;
        if (StringUtils.isNotBlank(source) && StringUtils.isNotBlank(regEx)) {
            Pattern pattern = Pattern.compile(regEx);
            matcher = pattern.matcher(source);
        }
        return matcher;
    }

    /**
     * 获得第一个匹配
     *
     * @param source
     * @param regEx
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:02
     * @modify by user: {修改人}  2017/12/1 11:02
     * @modify by reason:
     */
    public static String getFirstMatchContent(String source, String regEx) {
        String matchContent = "";
        Matcher matcher = getMatcher(source, regEx);
        if (null != matcher && matcher.find()) {
            matchContent = matcher.group();
        }
        return matchContent;
    }

    /**
     * 获得所有匹配
     *
     * @param source
     * @param regEx
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:03
     * @modify by user: {修改人}  2017/12/1 11:03
     * @modify by reason:
     */
    public static String getAllMatchContent(String source, String regEx) {
        StringBuilder matchContent = new StringBuilder();
        Matcher matcher = getMatcher(source, regEx);
        if (null != matcher) {
            while (matcher.find()) {
                matchContent.append(matcher.group());
            }
        }
        return matchContent.toString();
    }

}
