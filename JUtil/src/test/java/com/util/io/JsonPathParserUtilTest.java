package com.util.io;

import com.google.gson.JsonElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JsonPathParserUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十月 9, 2017</pre>
 */
public class JsonPathParserUtilTest {

    String jsonData;

    @Before
    public void before() throws Exception {
//        jsonData = "{\"errorCode\":0,\"image\":\"http://10.9.164.116:8080/kms/services/rest/dataInfoService/downloadFile?id=00000001/temp033/226_132607546_9844&token=7a57a5a7ffffffffc1a0316369671314\",\"traceUuid\":\"BED3CF1B-A983-4916-8AAC-536F8C5BEFA2\",\"traceIdx\":1,\"id\":\"9\",\"targetAttrs\":\"{\\n\\t\\\"bkgUrl\\\":\\t\\\"http://10.9.164.116:8080/kms/services/rest/dataInfoService/downloadFile?id=00000001/temp033/226_132881490_209062&token=7a57a5a7ffffffffc1a0316369671314\\\",\\n\\t\\\"deviceChannel\\\":\\t1,\\n\\t\\\"deviceIP\\\":\\t\\\"10.10.165.79\\\",\\n\\t\\\"deviceId\\\":\\t\\\"5502ce741e4e43d28b76a80db6ab9e30\\\",\\n\\t\\\"deviceName\\\":\\t\\\"??????????????\\\",\\n\\t\\\"devicePort\\\":\\t8888,\\n\\t\\\"faceTime\\\":\\t\\\"2017-10-01 11:02:22.000\\\",\\n\\t\\\"rect\\\":\\t{\\n\\t\\t\\\"height\\\":\\t0.088000,\\n\\t\\t\\\"width\\\":\\t0.035000,\\n\\t\\t\\\"x\\\":\\t0.558000,\\n\\t\\t\\\"y\\\":\\t0.314000\\n\\t}\\n}\",\"faces\":[{\"faceId\":1,\"eyePosition\":{\"leftEye\":{\"x\":0.580016,\"y\":0.439500},\"rightEye\":{\"x\":0.697930,\"y\":0.447838}},\"faceRect\":{\"x\":0.347829,\"y\":0.278263,\"width\":0.340873,\"height\":0.619136},\"facePose\":{\"pitch\":23.584452,\"roll\":9.229742,\"yaw\":49.182152,\"confidence\":0},\"age\":{\"range\":5,\"value\":21,\"ageGroup\":2},\"gender\":{\"confidence\":0.999305,\"value\":1},\"glass\":{\"confidence\":0.898767,\"value\":1},\"smile\":{\"confidence\":0.999100,\"value\":1},\"ethnic\":{\"confidence\":0.999943,\"value\":1},\"faceIQA\":{\"detectQuality\":0,\"pointsQuality\":0,\"eyeDistance\":22.695938,\"colorful\":1,\"grayScale\":0,\"grayMean\":-1,\"grayVar\":-1,\"clearity\":-1,\"frontal\":0,\"uncovered\":0,\"totalQuality\":0},\"identify\":[{\"relationId\":1,\"maxsimilarity\":0.315263,\"candidate\":[{\"alarmId\":1,\"blacklist_id\":\"7\",\"human_data\":[{\"bkg_picurl\":\"\",\"face_id\":\"1\",\"face_picurl\":\"http://10.33.31.108/upload/photo/1506739564359_6edef364977946dea4ae0648df9f9c3d.jpg\",\"face_rect\":{\"height\":0,\"width\":0,\"x\":0,\"y\":0},\"similarity\":0.315263}],\"human_id\":\"73\",\"reserve_field\":\"\",\"similarity\":0.315263}]}]}]}";
        jsonData = "{\"isDefault\":\"0\",\"defaultTemplate\":\"此凭证用作访gsfdgdsafgsdfgsdf\"}";
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: getJsonObject(String jsonData)
     */
    @Test
    public void testGetJsonObject() throws Exception {
        JsonElement jsonElement = JsonPathParserUtil.getJsonObject(jsonData);
//        jsonElement=JsonPathParserUtil.findJsonObject(jsonElement,"faces[0].identify[0].candidate[0].human_id");
//        System.out.println(jsonElement.getAsString());
        jsonElement = JsonPathParserUtil.findJsonObject(jsonElement, "defaultTemplate");
    }



} 
