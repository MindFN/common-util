/*
 * @ProjectName: 综合安防
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2017年11月02日 17:34
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.temp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月02日 17:34
 * @description
 * @modified By:
 * @modifued reason:
 */
public class RegTest {
    @Test
    public void testRegEx() {
        String source = "joe\r\naaa\r\n";
        System.out.print(source);
        List<String> matchList = new ArrayList<String>();
        try {
            // t\r
//            Pattern regex = Pattern.compile("\\A\\w*\\Z", Pattern.MULTILINE);
            Pattern regex = Pattern.compile("^\\w*$", Pattern.MULTILINE);
            Matcher regexMatcher = regex.matcher(source);
            while (regexMatcher.find()) {
                matchList.add(regexMatcher.group());
            }
        } catch (PatternSyntaxException ex) {
            // Syntax error in the regular expression
        }
        System.out.println(matchList.size());
        System.out.println(matchList);

    }
}
