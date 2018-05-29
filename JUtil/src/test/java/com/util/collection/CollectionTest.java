/*
 * @ProjectName: 综合安防
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2017年10月27日 19:14
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年10月27日 19:14
 * @description
 * @modified By:
 * @modifued reason:
 */
public class CollectionTest {
    @Test
    public void testPredicate() {
        String[] strArr = {"1", "2", "3"};
        List<String> list = Arrays.asList(strArr);
        Collection<String> collection = Collections2.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return !input.equals("2");
            }
        });
        System.out.println(collection);
    }

    @Test
    public void testValuePredicate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "2");
        map.put("4", "3");
        map.put("5", "4");


    }

}
