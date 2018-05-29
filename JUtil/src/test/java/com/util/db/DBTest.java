/*
 * @ProjectName: 综合安防
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2017年12月09日 15:01
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.db;

import com.util.db.entity.Department;
import com.util.db.entity.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年12月09日 15:01
 * @description
 * @modified By:
 * @modifued reason:
 */
public class DBTest {

    Configuration config;
    SessionFactory sessionFactory;
    Session session ;
    Transaction transaction;
    @Before
    public void init(){
        config=new Configuration().configure("hibernate.cfg.xml");
        sessionFactory=config.buildSessionFactory();
        session=sessionFactory.openSession();
        transaction.begin();
    }
    @After
    public void destroy(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void createTable(){
        config=new Configuration().configure("hibernate.cfg.xml");
        SchemaExport export = new SchemaExport(config);
        export.create(true, true);
    }


}
