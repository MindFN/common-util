package com.util.io.file.function;


import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

/**
 * 获取文件名
 *
 * @author wulang
 * @version v1.0
 * @date 2017年12月01日 14:01
 * @description
 * @modified By:
 * @modifued reason:
 */
public class FileNameFunction implements Function<String, String> {
    /**
     * 对象实例
     */
    private static volatile FileNameFunction INSTANCE;

    /**
     * 获取实例
     *
     * @param
     * @return
     * @author: wulang
     * @date: 2017/12/1 14:07
     * @modify by user: {修改人}  2017/12/1 14:07
     * @modify by reason:
     */
    public static FileNameFunction getInstance() {
        if (null == INSTANCE) {
            synchronized (FileNameFunction.class) {
                if (null == INSTANCE) {
                    INSTANCE = new FileNameFunction();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String apply(String input) {
        String fileName = "";
        if (StringUtils.isNotBlank(input)) {
            String[] fileRelateNameArray = input.split("[\\/]");
            fileName = fileRelateNameArray[fileRelateNameArray.length - 1];
        }
        return fileName;
    }
}
