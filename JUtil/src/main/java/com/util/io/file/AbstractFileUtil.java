package com.util.io.file;

import java.io.File;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年12月01日 12:53
 * @description
 * @modified By:
 * @modifued reason:
 */
public abstract class AbstractFileUtil {
    /**
     * 删除
     *
     * @param filePath
     * @return
     * @author: wulang
     * @date: 2017/11/13 16:34
     * @modify by user: {修改人}  2017/11/13 16:34
     * @modify by reason:
     */
    public static void deleteFileData(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
