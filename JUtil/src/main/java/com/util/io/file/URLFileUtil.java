package com.util.io.file;

import com.google.common.base.Predicate;
import com.util.io.file.exception.FileException;
import com.util.math.EncryptUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static com.util.io.file.exception.ExceptionConstant.*;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月13日 10:07
 * @description
 * @modified By:
 * @modifued reason:
 */
public class URLFileUtil extends AbstractFileUtil {
    /**
     * 写出数据
     *
     * @param fromUrl
     * @param basePath
     * @param relativePath
     * @return
     * @author: wulang
     * @date: 2017/11/13 16:28
     * @modify by user: {修改人}  2017/11/13 16:28
     * @modify by reason:
     */
    public static String writeFileData(String fromUrl, String basePath, String relativePath) throws IOException {
        String path = "";
        if (StringUtils.isNotBlank(fromUrl)) {
            path = writeFileData(fromUrl, basePath, relativePath, null, null);
        }
        return path;
    }

    /**
     * 根据URL数据
     *
     * @param fromUrl
     * @return
     * @author: wulang
     * @date: 2017/11/13 16:27
     * @modify by user: {修改人}  2017/11/13 16:27
     * @modify by reason:
     */
    public static String writeFileData(String fromUrl, String basePath, String relativePath, Predicate<String> urlValidate, Predicate<byte[]> dataValidate) {
        String filePath = "";
        boolean valid = true;
        if (StringUtils.isNotEmpty(fromUrl)) {
            byte[] fileData = null;
            String fileName = fetchFileName(fromUrl);
            valid = (null != urlValidate) ? urlValidate.apply(fromUrl) : valid;
            fileData = valid ? fetchFileData(fromUrl) : fileData;
            filePath = writeFileDataWithByte(fileData, basePath, relativePath, fileName, dataValidate);
        }
        return filePath;
    }

    /**
     * 直接写出byte数据到文件
     *
     * @param fileData
     * @param basePath
     * @param relativePath
     * @param fileName
     * @param dataValidate
     * @return
     * @author: wulang
     * @date: 2017/12/15 20:49
     * @modify by user: {修改人}  2017/12/15 20:49
     * @modify by reason:
     */
    public static String writeFileDataWithByte(byte[] fileData, String basePath, String relativePath, String fileName, Predicate<byte[]> dataValidate) {
        boolean valid = false;
        String filePath = "";
        if (null != fileData) {
            valid = null != dataValidate ? dataValidate.apply(fileData) : valid;
            if (valid) {
                checkFileDirExists(basePath + File.separator + relativePath);
                filePath = relativePath + File.separator + fileName;
                writeFileDataDetail(fileData, basePath + File.separator + filePath);
            }
        }
        return filePath;
    }

    /**
     * 检查路径是否存在
     *
     * @param path
     * @return
     * @author: wulang
     * @date: 2017/11/16 0:12
     * @modify by user: {修改人}  2017/11/16 0:12
     * @modify by reason:
     */
    private static void checkFileDirExists(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 从url获取byte数据
     *
     * @param fromUrl
     * @return
     * @author: wulang
     * @date: 2017/11/13 16:20
     * @modify by user: {修改人}  2017/11/13 16:20
     * @modify by reason:
     */
    public static final byte[] fetchFileData(String fromUrl) {
        int len = -1;
        byte[] buffer = null;
        ByteArrayOutputStream byteOS = null;
        BufferedInputStream is = null;
        URLConnection connection = null;
        try {
            connection = new URL(fromUrl).openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            buffer = new byte[1024 * 100];
            byteOS = new ByteArrayOutputStream();
            while (-1 != (len = is.read(buffer))) {
                byteOS.write(buffer, 0, len);
            }
            byteOS.flush();
        } catch (IOException e) {
//            LogUtils.logException(e);
            throw new FileException(FILE_NOT_EXISTS, ERROR_MESSAGE.get(FILE_NOT_EXISTS));
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteOS.toByteArray();
    }

    /**
     * 写出人脸图片数据到指定路径
     *
     * @param fileData
     * @param toPath
     * @return
     * @author: wulang
     * @date: 2017/11/13 16:18
     * @modify by user: {修改人}  2017/11/13 16:18
     * @modify by reason:
     */
    private static final void writeFileDataDetail(byte[] fileData, String toPath) {
        int len = -1;
        byte[] buffer = new byte[1024 * 100];
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toPath));
             BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(fileData))) {
            while (-1 != (len = bis.read(buffer))) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
//            LogUtils.logException(e);
            throw new FileException(FILE_COUNT_NOT_WRITE, ERROR_MESSAGE.get(FILE_COUNT_NOT_WRITE));
        }
    }

    /**
     * 获取文件名
     *
     * @param originFileName
     * @return
     * @author: wulang
     * @date: 2017/11/13 13:51
     * @modify by user: {修改人}  2017/11/13 13:51
     * @modify by reason:
     */
    private static String fetchFileName(String originFileName) {
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));
        String fileName = originFileName.substring(originFileName.lastIndexOf("/"), originFileName.length()).toLowerCase() + System.nanoTime() + "_" + Math.random();
        fileName = EncryptUtil.MD5Encode(fileName);
        fileName = fileName.substring(8, 24) + suffix;
        return fileName;
    }
}
