/*
 * @ProjectName: 综合安防
 * @Copyright: 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date:  2018年01月16日 18:45
 * @description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */
package com.util.io.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * @author wulang
 * @version v1.0
 * @date 2018年01月16日 18:45
 * @description
 * @modified By:
 * @modifued reason:
 */
public class CsvUtil {

    private static CsvWriter csvWriter;
    private static CsvReader csvReader;

    /**
     * 从csv格式中读取文件
     *
     * @param fileInputStream
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 21:06
     * @modify by user: {修改人}  2018/1/16 21:06
     * @modify by reason:
     */
    public static final <T> Collection<T> readDataFromCsv(FileInputStream fileInputStream, Class<T> type) throws IOException {
        checkReader();
        return csvReader.readDataFromCsv(fileInputStream, CsvConstant.DEFAULT_CHARSET, type);
    }

    /**
     * 从csv格式中读取文件
     *
     * @param fileInputStream
     * @param charSetName
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 21:07
     * @modify by user: {修改人}  2018/1/16 21:07
     * @modify by reason:
     */
    public static final <T> Collection<T> readDataFromCsv(FileInputStream fileInputStream, String charSetName, Class<T> type) throws IOException {
        checkReader();
        Charset charset = Charset.forName(charSetName);
        return csvReader.readDataFromCsv(fileInputStream, charset, type);
    }

    /**
     * 写出数据到csv文件
     *
     * @param outputStream
     * @param collection
     * @param hasTitle
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 21:07
     * @modify by user: {修改人}  2018/1/16 21:07
     * @modify by reason:
     */
    public static final <T> void writeDataToCsv(OutputStream outputStream, Collection<T> collection, boolean hasTitle, Class<T> type) throws IOException {
        checkWriter();
        csvWriter.writeDataToCsv(outputStream, CsvConstant.DEFAULT_CHARSET, collection, hasTitle, type);
    }

    /**
     * 写出数据到csv文件
     *
     * @param outputStream
     * @param charSetName
     * @param collection
     * @param hasTitle
     * @param type
     * @return
     * @author wulang
     * @date 2018/1/16 21:07
     * @modify by user: {修改人}  2018/1/16 21:07
     * @modify by reason:
     */
    public static final <T> void writeDataToCsv(OutputStream outputStream, String charSetName, Collection<T> collection, boolean hasTitle, Class<T> type) throws IOException {
        checkWriter();
        Charset charset = Charset.forName(charSetName);
        csvWriter.writeDataToCsv(outputStream, charset, collection, hasTitle, type);
    }

    /**
     * @param
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:02
     * @modify by user: {修改人}  2017/10/14 16:02
     * @modify by reason:
     */
    private static void checkWriter() {
        if (null == csvWriter) {
            csvWriter = CsvWriter.getInstance();
        }
    }

    /**
     * @param
     * @return
     * @author: wulang
     * @date: 2017/10/14 16:02
     * @modify by user: {修改人}  2017/10/14 16:02
     * @modify by reason:
     */
    private static void checkReader() {
        if (null == csvReader) {
            csvReader = CsvReader.getInstance();
        }
    }
}
