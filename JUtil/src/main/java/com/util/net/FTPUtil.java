package com.util.net;

import com.util.common.ObjectUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * FTP上传文件的工具类
 *
 * @author: wulang
 * @date: 2017/9/28 17:35
 * @modify by user: {修改人}  2017/9/28 17:35
 * @modify by reason:
 * @return
 */
public class FTPUtil {
    static final byte FTP_PORT = 21;
    static final String PATH_SEPARATOR = "/";

    /**
     * 默认为覆盖模式
     *
     * @param ftpIp
     * @param username
     * @param password
     * @param filePath
     * @param file
     * @return
     * @author: wulang
     * @date: 2017/9/28 17:33
     * @modify by user: {修改人}  2017/9/28 17:33
     * @modify by reason:
     */
    public static void uploadFile(String ftpIp, String username, String password, String filePath, File file) {
        uploadFile(ftpIp, username, password, filePath, file, false);
    }

    /**
     * 上传文件到FTP服务器
     *
     * @param filePath
     * @param ftpIp
     * @param username
     * @param password
     * @param file
     * @param appendMode 是否为追加模式
     * @return
     * @author: wulang
     * @date: 2017/9/28 17:00
     * @modify by user: {修改人}  2017/9/28 17:00
     * @modify by reason:
     */
    public static void uploadFile(String ftpIp, String username, String password, String filePath, File file, boolean appendMode) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(ftpIp, FTP_PORT);
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (ObjectUtil.isNull(file)) {
                return;
            }
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            String fileName = file.getName();
            if (!filePath.startsWith(PATH_SEPARATOR)) {
                filePath = PATH_SEPARATOR + filePath;
            }
            if (!ftp.changeWorkingDirectory(filePath)) {
                String[] dirs = filePath.split(PATH_SEPARATOR);
                StringBuilder tempPath = new StringBuilder();
                for (String dir : dirs) {
                    if (ObjectUtil.isNull(dir)){
                        continue;
                    }
                    tempPath.append(PATH_SEPARATOR).append(dir);
                    if (!ftp.changeWorkingDirectory(tempPath.toString())) {
                        if (!ftp.makeDirectory(tempPath.toString())) {
//							LogUtils.logInfo("create ftp path {} failed ...", tempPath);
                        } else {
                            ftp.changeWorkingDirectory(tempPath.toString());
                        }
                    }
                }
            }
            InputStream is = ftp.retrieveFileStream(fileName);
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            if (appendMode && !ObjectUtil.isNull(is)) {
                is.close();
                ftp.completePendingCommand();
                ftp.appendFile(fileName, bis);
            } else {
                ftp.storeFile(fileName, bis);
            }
            bis.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
//			LogUtils.logException(e);
        } finally {
            if (ftp.isConnected()){
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除指定路径下FTP文件
     *
     * @param filePath
     * @param ftpIp
     * @param username
     * @param password
     * @return
     * @author: wulang
     * @date: 2017/9/28 17:02
     * @modify by user: {修改人}  2017/9/28 17:02
     * @modify by reason:
     */
    public static void deleteFile(String ftpIp, String username, String password, String filePath) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(ftpIp, FTP_PORT);
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            deleteFile(ftp, filePath);
        } catch (IOException e) {
//			LogUtils.logException(e);
        } finally {
            if (ftp.isConnected()){

                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件的细节
     *
     * @param ftp
     * @param path
     * @return
     * @author: wulang
     * @date: 2017/9/28 17:34
     * @modify by user: {修改人}  2017/9/28 17:34
     * @modify by reason:
     */
    private static void deleteFile(FTPClient ftp, String path) {
        try {
            ftp.changeWorkingDirectory(path);
            if (isDir(ftp, path)) {
                FTPFile[] files = ftp.listFiles(path);
                for (FTPFile file : files) {
                    if ("..".equals(file.getName()) || ".".equals(file.getName())){
                        continue;
                    }
                    deleteFile(ftp, path + PATH_SEPARATOR + file.getName());
                    ftp.removeDirectory(path + PATH_SEPARATOR + file.getName());
                }
                ftp.removeDirectory(path);
            } else {
                ftp.deleteFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否为目录
     *
     * @param ftp
     * @param path
     * @return
     * @author: wulang
     * @date: 2017/9/28 17:34
     * @modify by user: {修改人}  2017/9/28 17:34
     * @modify by reason:
     */
    private static boolean isDir(FTPClient ftp, String path) throws IOException {
        return ftp.listFiles(path).length > 1;
    }
}
