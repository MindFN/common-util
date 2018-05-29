package com.util.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.IOException;
import java.util.Arrays;

/**
 * CNStringUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十月 13, 2017</pre>
 */
public class CNStringUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: fetchFirstCharacterFromString(String srcStr)
     */
    @Test
    public void testFetchFirstCharacterFromString() throws Exception {
        String source = "F963UF91XXF59Y9F6211F6765F4V86";
        System.out.println(CNStringUtil.fetchFirstCharacterFromString(source));
    }

    /**
     * Method: isChineseCharacter(char src)
     */
    @Test
    public void testIsChineseCharacter() throws Exception {

    }

    @Test
    public void testCh() {

        String[] chArr = new String['a' - 'A'];
        Character ch = 'a';
        for (; ch <= 'z'; ch++) {
            chArr[ch - 'a'] = "'"+ch+"'";
        }
        System.out.println(Arrays.toString(chArr));

    }
    @Test
    public void decodeString() throws IOException {

    }



} 
