package com.util.io;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.util.common.RegUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 手动解析Json格式对象
 * <p>
 * 示例：
 * <br>
 * 查找[{key:value},{key:value,key2:value2}]中key2对应的path:[1].key2;<br>
 * 查找{key:value,key2:value2}中key2对应的path:key2
 *
 * @Author: wulang
 * @Date: 2017年10月09日 14:12
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class JsonPathParserUtil {
    private final static JsonParser parser = new JsonParser();
    /**
     * 非数组的情况下为默认，节点的下标为默认节点
     */
    private final static String DEFAULT_INDEX = "-1";

    private final static String NODE_REGEX = "\\.";
    private final static String INDEX_REGEX = "(?<=\\[)\\d(?=\\])";
    private final static String TAG_REGEX = "\\w*(?=(\\[?))";


    /***
     * 从jsonData中获取数据
     * @author: wulang
     * @date: 2017/11/21 14:34
     * @param jsonText
     * @param jsonPath
     * @modify by user: {修改人}  2017/11/21 14:34
     * @modify by reason:
     * @return
     */
    public static String fetchJsonValue(String jsonText, String jsonPath) {
        JsonElement jsonElement = JsonPathParserUtil.getJsonObject(jsonText);
        jsonElement = JsonPathParserUtil.findJsonObject(jsonElement, jsonPath);
        return null != jsonElement ? jsonElement.getAsString() : "";
    }

    /**
     * 根据传递的json数据获取 json节点元素
     *
     * @param jsonData
     * @return
     * @author: wulang
     * @date: 2017/10/9 15:08
     * @modify by user: {修改人}  2017/10/9 15:08
     * @modify by reason:
     */
    public static JsonElement getJsonObject(String jsonData) {
        if (StringUtils.isBlank(jsonData)) {
            return null;
        }
        JsonElement jsonElement = parser.parse(jsonData);
        return jsonElement;
    }

    /**
     * 根据指定的path查找json节点数据
     * <p>
     * <p>
     * [\d].tagName0.TagName1[\d].tagName2
     * </p>
     *
     * @param jsonElement
     * @param path
     * @return
     * @author: wulang
     * @date: 2017/10/9 15:22
     * @modify by user: {修改人}  2017/10/9 15:22
     * @modify by reason:
     */
    public static JsonElement findJsonObject(JsonElement jsonElement, String path) {
        if (null == jsonElement) {
            return null;
        }
        List<JsonNode> jsonNodeList = parseJsonPath(path);
        JsonElement cJsonObject = jsonElement;
        for (JsonNode jsonNode : jsonNodeList) {
            try {
                cJsonObject = nextJsonElement(cJsonObject, jsonNode);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(jsonNode);
                return null;
            }
        }
        return cJsonObject;
    }

    /**
     * 根据 jsonNode从原始数据中获取json的具体方法
     *
     * @param pJsonElement
     * @param jsonNode
     * @return
     * @author: wulang
     * @date: 2017/10/9 15:23
     * @modify by user: {修改人}  2017/10/9 15:23
     * @modify by reason:
     */
    private static JsonElement nextJsonElement(JsonElement pJsonElement, JsonNode jsonNode) {
        JsonElement cJsonElement = null;
        JsonArray cJsonArray = null;
        JsonObject cJsonObject = null;
        if (null != jsonNode && null != pJsonElement) {
            cJsonElement = pJsonElement;
            if (cJsonElement.isJsonObject()) {
                cJsonObject = cJsonElement.getAsJsonObject();
                cJsonElement = cJsonObject.get(jsonNode.getTagName());
            }
            if (!DEFAULT_INDEX.equals(jsonNode.getIndex())) {
                if (cJsonElement.isJsonArray()) {
                    cJsonArray = cJsonElement.getAsJsonArray();
                    cJsonElement = cJsonArray.get(Integer.valueOf(jsonNode.getIndex()));
                } else {
                    cJsonObject = cJsonElement.getAsJsonObject();
                    cJsonElement = cJsonObject.get(jsonNode.getIndex());
                }
            }
        }
        return cJsonElement;
    }

    /**
     * 解析查找路径
     *
     * @param path
     * @return
     * @author: wulang
     * @date: 2017/10/9 15:25
     * @modify by user: {修改人}  2017/10/9 15:25
     * @modify by reason:
     */
    private static List<JsonNode> parseJsonPath(String path) {
        List<JsonNode> jsonNodeList = Lists.newArrayList();
        String[] nodeArr = path.split(NODE_REGEX);
        JsonNode jsonNode = null;
        String index = null;
        String tagName = null;
        for (String node : nodeArr) {
            jsonNode = new JsonNode();
            index = RegUtil.getFirstMatchContent(node, INDEX_REGEX);
            tagName = RegUtil.getFirstMatchContent(node, TAG_REGEX);
            jsonNode.setTagName(tagName);
            if (StringUtils.isNotBlank(index)) {
                jsonNode.setIndex(index);
            }
            jsonNodeList.add(jsonNode);
        }
        return jsonNodeList;
    }

    /**
     * json节点标签标识
     *
     * @author: wulang
     * @date: 2017/10/9 15:25
     * @modify by user: {修改人}  2017/10/9 15:25
     * @modify by reason:
     * @return
     */
    private static class JsonNode {
        private String tagName;
        private String index;

        public JsonNode() {
            index = DEFAULT_INDEX;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "JsonNode{" +
                    "tagName='" + tagName + '\'' +
                    ", index=" + index +
                    '}';
        }
    }
}
