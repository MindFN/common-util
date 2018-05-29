package com.util.net;

import com.util.common.ObjectUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * FTPUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>¾ÅÔÂ 28, 2017</pre>
 */
public class FTPUtilTest {
    String ip;
    String username;
    String password;
    String filePath;
    File file;

    @Before
    public void before() throws Exception {
        ip = "10.33.31.94";
        username = "hikftp";
        password = "hik12345";
        filePath = "/lang";
        file = getTestFile();
    }

    @After
    public void after() throws Exception {
        if (!ObjectUtil.isNull(file)) {
            file.delete();
        }
    }

    /**
     * Method: uploadFile(String basePath, String ftpIp, String username, String password, File file)
     */
    @Test
    public void testUploadFile() throws Exception {


//        FTPUtil.uploadFile(ip, username, password, filePath, file, true);


        System.out.println(getTestFile().exists());


    }

    /**
     * Method: deleteFile(String filePath, String ftpIp, String username, String password)
     */
    @Test
    public void testDeleteFile() throws Exception {
        FTPUtil.deleteFile(ip, username, password, filePath);
    }


    public File getTestFile() throws URISyntaxException {
        URI uri=new URI("http://10.10.161.115:8088/image/3/3/8e8cd2eaea9b4e23aeb4e74fdef56e11/0/0/4c62/305412/76046");

        File file = new File(new URI(uri.toString()));
        return file;
    }

} 
