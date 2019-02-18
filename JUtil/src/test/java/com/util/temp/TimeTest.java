/*
 * @ProjectName: 综合安防
 * @Copyright:   2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address:     http://www.hikvision.com
 * @date:        2018年01月24日 12:15
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.temp;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.util.collection.Tree.TreeNode;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月24日 12:15
 * @modify By:
 * @modify reason:
 */
public class TimeTest {
    @Test
    public void testInstant() {
        Retryer<TreeNode> retry = RetryerBuilder.<TreeNode>newBuilder().retryIfResult(new Predicate<TreeNode>() {
            @Override
            public boolean apply(@Nullable TreeNode input) {
                return input.getNode().equals("");
            }
        }).build();
        // System.out.println(Timestamp.from(Instant.now()));
    }

    public void testApplicationContext() {

        new ClassPathXmlApplicationContext().close();
        ;

    }

}
