/*
 * @ProjectName: 综合安防
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2017年11月16日 16:27
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.temp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月16日 16:27
 * @description
 * @modified By:
 * @modifued reason:
 */
public class MQTest {


    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    ConnectionFactory connectionFactory;

    Connection connection;

    Session session;

    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();
    AtomicInteger count = new AtomicInteger();

    @Before
    public void init() {
        try {
            connectionFactory = new ActiveMQConnectionFactory();
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public void getMessage(String disname) {
        try {
            Queue queue = session.createQueue(disname);
            MessageConsumer consumer = null;

            if (threadLocal.get() != null) {
                consumer = threadLocal.get();
            } else {
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }
            while (true) {
                Thread.sleep(1000);
                MapMessage msg = (MapMessage) consumer.receive();
                if (msg != null) {
                    msg.acknowledge();
                    System.out.println(Thread.currentThread().getName() + ": Consumer:我是消费者，我正在消费Msg" + msg.toString() + "--->" + count.getAndIncrement());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMQQueue() {

        getMessage("cms.acs.otherPlatform.msg.queue");
    }
}
