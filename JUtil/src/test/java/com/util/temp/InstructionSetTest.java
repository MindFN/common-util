/*
 * @ProjectName: 综合安防
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2017年11月02日 15:16
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.temp;

import org.junit.Test;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月02日 15:16
 * @description
 * @modified By:
 * @modifued reason:
 */
public class InstructionSetTest {
    @Test
    public void testInc() {
        Integer i = 0;

//Integer.valueOf()
        i.intValue();
        for (int j = 0; j < 10; j++) {
            i++;
        }
        System.out.println(i);
    }

}
