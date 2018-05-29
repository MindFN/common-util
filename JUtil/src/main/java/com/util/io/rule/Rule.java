package com.util.io.rule;

import com.util.io.rule.funciton.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wulang
 * @Date: 2017年10月09日 16:55
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public enum Rule {

    DEFAULT_FUNCTION(1), DATE_FORMAT_FUNCTION(2), DATE_PARSE_FUNCTION(3), BASE_TYPE_FUNCTION(4);

    static Map<Integer, BaseFunction<?>> functionMap = new HashMap<>();

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    static {
        functionMap.put(1, new DefaultFunction());
        functionMap.put(2, new DateFormatFunction());
        functionMap.put(3, new DateParseFunction());
        functionMap.put(4, new BasicTypeFunction());
    }

    Rule(int type) {
        this.type = type;
    }

    public BaseFunction<?> getFunction() {
        return functionMap.get(type);
    }
}
