package com.util.io.excel;

/**
 * @author: wulang
 * @Date: 2017年10月14日 15:26
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class ExcelConstant {
    /**
     * xlsx最大行数
     */
    public static final int XLSX_MAX_ROW = 0xFFFFF + 1;
    /**
     * xlsx最大列数
     */
    public static final int XLSX_MAX_COL = 0x3FFF + 1;
    /**
     * xls最大行数
     */
    public static final int XLS_MAX_ROW = 0xFFFF + 1;
    /**
     * xls最大列数
     */
    public static final int XLS_MAX_COL = 0xFF + 1;
    /**
     * 单元格最大字符长度
     */
    public static final int DATA_MAX_LENGTH = 0x7FFF;
    /**
     * 纯粹写数据时的批量缓存
     */
    public static final int BATCH_WRITE_SIZE = 0x2FFFF;

}
