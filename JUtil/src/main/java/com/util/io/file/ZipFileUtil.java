package com.util.io.file;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.util.io.file.constant.FileConstant;
import com.util.io.file.function.ExcludeSuffixFunction;
import com.util.io.file.function.FileNameFunction;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by wulang on 2017/7/3.
 *
 * @author wulang
 */
public class ZipFileUtil extends AbstractFileUtil {
    public static final Charset DEFAULT_ZIP_CHARSET = Charset.forName(FileConstant.DEFAULT_ZIP_ENCODE_WIN);
    /**
     * 解压文件,获取文件夹中所有图片文件
     *
     * @param file
     * @return
     */
//    public static File uncompressFile(MultipartFile file) {
//        //todo
//        String uploadTempPath = FileUploadUtils.getUploadFileTempPath();
//        File destFile = new File(uploadTempPath + File.separator + file.getOriginalFilename());
//        try {
//            file.transferTo(destFile);
//        } catch (Exception e) {
//            LogUtils.logException(e);
//            LogUtils.logInfo("文件[{}]格式错误,解压失败", file.getOriginalFilename());
//        }
//        return destFile;
//
//    }


    /**
     * 获取所有文件名不含后缀
     *
     * @param zipFile
     * @return
     * @author: wulang
     * @date: 2017/12/1 14:13
     * @modify by user: {修改人}  2017/12/1 14:13
     * @modify by reason:
     */
    public static List<String> getOrginFileName(ZipFile zipFile) {
        return Lists.transform(Lists.transform(getFileRelativePath(zipFile), FileNameFunction.getInstance()), ExcludeSuffixFunction.getInstance());
    }

    /**
     * 获取文件中的包含相对路径的文件名
     *
     * @param zipFile
     * @return
     */
    public static List<String> getFileRelativePath(ZipFile zipFile) {
        return Lists.newArrayList(getZipEntryMapMap(zipFile).keySet());
    }

    /**
     * 解析zip实体
     *
     * @param zipFile
     * @return
     * @author: wulang
     * @date: 2017/12/1 13:37
     * @modify by user: {修改人}  2017/12/1 13:37
     * @modify by reason:
     */
    public static Map<String, ZipEntry> getZipEntryMapMap(ZipFile zipFile) {
        Map<String, ZipEntry> zipEntryMap = Maps.newHashMap();
        if (null != zipFile && zipFile.size() > 0) {
            ZipEntry entry = null;
            Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                entry = enumeration.nextElement();
                if (!entry.isDirectory()) {
                    zipEntryMap.put(entry.getName(), entry);
                }
            }

        }
        return zipEntryMap;
    }


    /**
     * 将符合personCode的文件保存到指定文件夹下
     * <p>
     * <p>
     * 保存zip文件到指定目录下，fileNameMap为null时，则不进行过滤，否则按照fileNameMap过滤
     * <p>
     * </p>
     *
     * @param zipFile
     * @param basePath
     * @param fileNameMap <path,fileName/>
     * @return
     * @author: wulang
     * @date: 2017/12/1 13:54
     * @modify by user: {修改人}  2017/12/1 13:54
     * @modify by reason:
     */
    public static void saveOrUpdateFile(ZipFile zipFile, String basePath, Map<String, String> fileNameMap) throws IOException {
        ZipEntry zipEntry = null;
        String path = null;
        String fileName = null;
        File file = new File(basePath);
        if (null == zipFile || zipFile.size() == 0) {
            return;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        Map<String, ZipEntry> zipEntryMap = getZipEntryMapMap(zipFile);
        Map.Entry<String, ZipEntry> entry = null;
        Iterator<Map.Entry<String, ZipEntry>> iterator = zipEntryMap.entrySet().iterator();
        while (iterator.hasNext()) {
            entry = iterator.next();
            path = entry.getKey();
            zipEntry = entry.getValue();
            fileName = null == fileNameMap ? FileNameFunction.getInstance().apply(path) : fileNameMap.get(path);
            if (!zipEntry.isDirectory() && StringUtils.isNotBlank(fileName)) {
                file = new File(basePath + File.separator + fileName);
                //删除已经存在的文件
                if (file.exists()) {
                    file.delete();
                }
                //压缩文件中的图片存在多级目录时,检查父目录,在父目录不存在的情况下,创建
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                writeFile(zipFile, zipEntry, file);
            }
        }

    }

    /**
     * 解压缩
     *
     * @param zipFile
     * @param entry
     * @param target
     * @return
     * @author: wulang
     * @date: 2017/12/1 13:39
     * @modify by user: {修改人}  2017/12/1 13:39
     * @modify by reason:
     */
    private static void writeFile(ZipFile zipFile, ZipEntry entry, File target) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target))
        ) {
            int len = -1;
            byte[] buffer = new byte[1024 * 1024];
            while (-1 != (len = bis.read(buffer))) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
        }
    }

}
