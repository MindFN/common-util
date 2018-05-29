package com.util.io;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * 使用js 脚本引擎实现json的解析--》仅限jdk7使用
 * <p>
 * 示例：
 * <br>
 * 查找[{key:value},{key:value,key2:value2}]中key2对应的path:[1].key2;
 * <br>
 * 查找{key:value,key2:value2}中key2对应的path:.key2
 *
 *
 * </p>
 * @author: wulang
 * @Date: 2017年10月09日 14:12
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class JsonScriptParserUtil {
    private final static ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
    /**
     * 使用js脚本引擎解析js，在1.7可以使用，1.8脚本引擎有变化
     * @author: wulang
     * @date: 2017/10/9 16:10
     * @param jsonData
     * @param path
     * @modify by user: {修改人}  2017/10/9 16:10
     * @modify by reason:
     * @return
     */
    public static Object findJsonObject(String jsonData, String path) {
        Object value = null;
        try {
            scriptEngine.eval("var json=" + jsonData);
            value = scriptEngine.eval("json" + path);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return value;
    }

}
