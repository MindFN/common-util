package com.util.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月15日 10:32
 * @description
 * @modified By:
 * @modifued reason:
 */
public class JsonMapUtil {
    /**
     * 映射json数据到map
     *
     * @param src
     * @return
     * @author: wulang
     * @date: 2017/11/15 10:32
     * @modify by user: {修改人}  2017/11/15 10:32
     * @modify by reason:
     */
    public static Map<String, String> getAttributeMap(String src) {
        Map<String, String> result = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Map<String, Object>> maps = objectMapper.readValue(src, Map.class);
            for (Map.Entry<String, Map<String, Object>> entity : maps.entrySet()) {
                if (entity.getValue() != null && !"null".equals(String.valueOf(entity.getValue()))) {
                    result.put(entity.getKey(), String.valueOf(entity.getValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
