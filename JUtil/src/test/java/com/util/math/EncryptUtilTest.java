package com.util.math; 

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* EncryptUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 9, 2017</pre> 
* @version 1.0 
*/ 
public class EncryptUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

    @Test
    public void testOperator(){
        String source="中文";
        System.out.println(EncryptUtil.ContentDecoder(EncryptUtil.ContentEncode(source)));


    }
}
