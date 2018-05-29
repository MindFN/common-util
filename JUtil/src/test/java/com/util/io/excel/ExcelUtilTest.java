package com.util.io.excel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Date;

/**
 * ExcelUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Ê®ÔÂ 17, 2017</pre>
 */
public class ExcelUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
    @Test
    public void testType(){
        Class<?> fieldType=null;
        fieldType=Double.class;
        if (Number.class.isAssignableFrom(fieldType)) {
            System.out.println("Number"+fieldType);
        }
        if (fieldType.isAssignableFrom(Double.TYPE) || fieldType.isAssignableFrom(Double.class)) {
            System.out.println("double"+fieldType);
        }
        if (fieldType.isAssignableFrom(Integer.TYPE) || fieldType.isAssignableFrom(Integer.class)) {
            System.out.println("integer"+fieldType);
        }
        if (fieldType.isAssignableFrom(Float.TYPE) || fieldType.isAssignableFrom(Float.class)) {
            System.out.println("float"+fieldType);
        }
        if (fieldType.isAssignableFrom(Character.TYPE) || fieldType.isAssignableFrom(Character.class)) {
            System.out.println("char"+fieldType);
        }
        if (fieldType.isAssignableFrom(Short.TYPE) || fieldType.isAssignableFrom(Short.class)) {
            System.out.println("short"+fieldType);
        }
        if (fieldType.isAssignableFrom(Byte.TYPE) || fieldType.isAssignableFrom(Byte.class)) {
              System.out.println("byte"+fieldType);
        }
        if (fieldType.isAssignableFrom(Boolean.TYPE) || fieldType.isAssignableFrom(Boolean.class)) {
            System.out.println("boolean"+fieldType);
        }
        if (fieldType.isAssignableFrom(Long.TYPE) || fieldType.isAssignableFrom(Long.class)) {
            System.out.println("long"+fieldType);
        }
        if (fieldType.isAssignableFrom(String.class)) {
            System.out.println("String");
        }
        if (fieldType.isAssignableFrom(Date.class)) {
            System.out.println("date");
        }
    }

} 
