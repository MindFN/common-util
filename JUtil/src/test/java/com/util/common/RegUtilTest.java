package com.util.common;

import com.google.gson.JsonElement;
import com.util.date.DateFormatUtil;
import com.util.io.JsonPathParserUtil;
import org.junit.Test;

import java.util.Date;

/**
 * RegUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 29, 2017</pre>
 */
public class RegUtilTest {
    String data;
    String jsonData;
    String dataReg;
    String dataStr = "";
    String personCode = "";
    Date faceTime = null;

    public void before() throws Exception {
        dataReg = "\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
        data = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ExtEventInfo><JsonData>[{\"errorCode\":0,\"image\":\"http://10.9.164.116:8080/kms/services/rest/dataInfoService/downloadFile?id=00000001/temp033/226_132607546_9844&token=7a57a5a7ffffffffc1a0316369671314\",\"traceUuid\":\"BED3CF1B-A983-4916-8AAC-536F8C5BEFA2\",\"traceIdx\":1,\"id\":\"9\",\"targetAttrs\":\"{\\n\\t\\\"bkgUrl\\\":\\t\\\"http://10.9.164.116:8080/kms/services/rest/dataInfoService/downloadFile?id=00000001/temp033/226_132881490_209062&token=7a57a5a7ffffffffc1a0316369671314\\\",\\n\\t\\\"deviceChannel\\\":\\t1,\\n\\t\\\"deviceIP\\\":\\t\\\"10.10.165.79\\\",\\n\\t\\\"deviceId\\\":\\t\\\"5502ce741e4e43d28b76a80db6ab9e30\\\",\\n\\t\\\"deviceName\\\":\\t\\\"����Ͳ������ץ��\\\",\\n\\t\\\"devicePort\\\":\\t8888,\\n\\t\\\"faceTime\\\":\\t\\\"2017-10-01 11:02:22.000\\\",\\n\\t\\\"rect\\\":\\t{\\n\\t\\t\\\"height\\\":\\t0.088000,\\n\\t\\t\\\"width\\\":\\t0.035000,\\n\\t\\t\\\"x\\\":\\t0.558000,\\n\\t\\t\\\"y\\\":\\t0.314000\\n\\t}\\n}\",\"faces\":[{\"faceId\":1,\"eyePosition\":{\"leftEye\":{\"x\":0.580016,\"y\":0.439500},\"rightEye\":{\"x\":0.697930,\"y\":0.447838}},\"faceRect\":{\"x\":0.347829,\"y\":0.278263,\"width\":0.340873,\"height\":0.619136},\"facePose\":{\"pitch\":23.584452,\"roll\":9.229742,\"yaw\":49.182152,\"confidence\":0},\"age\":{\"range\":5,\"value\":21,\"ageGroup\":2},\"gender\":{\"confidence\":0.999305,\"value\":1},\"glass\":{\"confidence\":0.898767,\"value\":1},\"smile\":{\"confidence\":0.999100,\"value\":1},\"ethnic\":{\"confidence\":0.999943,\"value\":1},\"faceIQA\":{\"detectQuality\":0,\"pointsQuality\":0,\"eyeDistance\":22.695938,\"colorful\":1,\"grayScale\":0,\"grayMean\":-1,\"grayVar\":-1,\"clearity\":-1,\"frontal\":0,\"uncovered\":0,\"totalQuality\":0},\"identify\":[{\"relationId\":1,\"maxsimilarity\":0.315263,\"candidate\":[{\"alarmId\":1,\"blacklist_id\":\"7\",\"human_data\":[{\"bkg_picurl\":\"\",\"face_id\":\"1\",\"face_picurl\":\"http://10.33.31.108/upload/photo/1506739564359_6edef364977946dea4ae0648df9f9c3d.jpg\",\"face_rect\":{\"height\":0,\"width\":0,\"x\":0,\"y\":0},\"similarity\":0.315263}],\"human_id\":\"73\",\"reserve_field\":\"\",\"similarity\":0.315263}]}]}]}]</JsonData><DevInfo ip=\"10.10.165.79\" port=\"8888\" chan=\"1\" camerindexcode=\"5502ce741e4e43d28b76a80db6ab9e30\"/></ExtEventInfo>";
        jsonData = "{\"errorCode\":0,\"image\":\"http://10.9.164.116:8080/kms/services/rest/dataInfoService/downloadFile?id=00000001/temp033/226_132607546_9844&token=7a57a5a7ffffffffc1a0316369671314\",\"traceUuid\":\"BED3CF1B-A983-4916-8AAC-536F8C5BEFA2\",\"traceIdx\":1,\"id\":\"9\",\"targetAttrs\":\"{\\n\\t\\\"bkgUrl\\\":\\t\\\"http://10.9.164.116:8080/kms/services/rest/dataInfoService/downloadFile?id=00000001/temp033/226_132881490_209062&token=7a57a5a7ffffffffc1a0316369671314\\\",\\n\\t\\\"deviceChannel\\\":\\t1,\\n\\t\\\"deviceIP\\\":\\t\\\"10.10.165.79\\\",\\n\\t\\\"deviceId\\\":\\t\\\"5502ce741e4e43d28b76a80db6ab9e30\\\",\\n\\t\\\"deviceName\\\":\\t\\\"����Ͳ������ץ��\\\",\\n\\t\\\"devicePort\\\":\\t8888,\\n\\t\\\"faceTime\\\":\\t\\\"2017-10-01 11:02:22.000\\\",\\n\\t\\\"rect\\\":\\t{\\n\\t\\t\\\"height\\\":\\t0.088000,\\n\\t\\t\\\"width\\\":\\t0.035000,\\n\\t\\t\\\"x\\\":\\t0.558000,\\n\\t\\t\\\"y\\\":\\t0.314000\\n\\t}\\n}\",\"faces\":[{\"faceId\":1,\"eyePosition\":{\"leftEye\":{\"x\":0.580016,\"y\":0.439500},\"rightEye\":{\"x\":0.697930,\"y\":0.447838}},\"faceRect\":{\"x\":0.347829,\"y\":0.278263,\"width\":0.340873,\"height\":0.619136},\"facePose\":{\"pitch\":23.584452,\"roll\":9.229742,\"yaw\":49.182152,\"confidence\":0},\"age\":{\"range\":5,\"value\":21,\"ageGroup\":2},\"gender\":{\"confidence\":0.999305,\"value\":1},\"glass\":{\"confidence\":0.898767,\"value\":1},\"smile\":{\"confidence\":0.999100,\"value\":1},\"ethnic\":{\"confidence\":0.999943,\"value\":1},\"faceIQA\":{\"detectQuality\":0,\"pointsQuality\":0,\"eyeDistance\":22.695938,\"colorful\":1,\"grayScale\":0,\"grayMean\":-1,\"grayVar\":-1,\"clearity\":-1,\"frontal\":0,\"uncovered\":0,\"totalQuality\":0},\"identify\":[{\"relationId\":1,\"maxsimilarity\":0.315263,\"candidate\":[{\"alarmId\":1,\"blacklist_id\":\"7\",\"human_data\":[{\"bkg_picurl\":\"\",\"face_id\":\"1\",\"face_picurl\":\"http://10.33.31.108/upload/photo/1506739564359_6edef364977946dea4ae0648df9f9c3d.jpg\",\"face_rect\":{\"height\":0,\"width\":0,\"x\":0,\"y\":0},\"similarity\":0.315263}],\"human_id\":\"73\",\"reserve_field\":\"\",\"similarity\":0.315263}]}]}]}";
    }

    public void after() throws Exception {
    }

    /**
     * Method: getMatchGroupContent(String str, String regex)
     */
    @Test
    public void testGetMatchGroupContent() throws Exception {

    }

    @Test
    public void testJsonPath() {
        JsonElement jsonElement = JsonPathParserUtil.getJsonObject(jsonData);
        jsonElement = JsonPathParserUtil.findJsonObject(jsonElement, "faces[0].identify[0].candidate[0].human_id");
        System.out.println(jsonElement.getAsString());
//        System.out.println(JsonScriptParserUtil.findJsonObject(jsonData, "[0].faces[0].identify[0].candidate[0].human_id"));

    }

    /**
     * Method: getMatchContent(String str, String regex)
     */
    public void testGetMatchContent() throws Exception {
        dataStr = RegUtil.getFirstMatchContent(data, "faceTime.*" + dataReg);
        dataStr = RegUtil.getFirstMatchContent(dataStr, dataReg);
        personCode = RegUtil.getFirstMatchContent(data, "human_id\":\"\\w*");
        System.out.println(personCode);
        personCode = RegUtil.getFirstMatchContent(personCode, "(?<=human_id\":\").*");
        if (!ObjectUtil.isNull(dataStr)) {
            faceTime = DateFormatUtil.parseDate(dataStr);
        }
        System.out.println(faceTime);
        System.out.println(personCode);

    }

    @Test
    public void testReg() {
        String data = "\b�ҁ(*\u00124228021994062013100�\u0001:\u0006吴浪@\u0001J\f默认部门R\u00040000X\u0001`�Υ`j\u00045602�\u0001\u0001�\u0001�\u0006<ExtEventInfo><ExtEventType>1</ExtEventType><ExtEventMainDevID>1</ExtEventMainDevID><ExtEventCode>83912960</ExtEventCode><ExtEventDoorID>1</ExtEventDoorID><ExtEventReaderKind>2</ExtEventReaderKind><ExtEventReaderID>1</ExtEventReaderID><ExtEventCardNo>422802199406201310</ExtEventCardNo><ExtEventInOut>1</ExtEventInOut><ExtEventPictureURL>http://127.0.0.1:0Failed-to-upload-picture</ExtEventPictureURL><ExtEventIdentityCardInfo><Name>����</Name><Sex>1</Sex><Nation>6</Nation><Birth>1994-6-20</Birth><Address>湖北省利川市元堡乡苦草村五组008号</Address><IdNum>422802199406201310</IdNum><IssuingAuthority>利川市公安局</IssuingAuthority><TermOfValidity>0</TermOfValidity><StartDate>2013-6-28</StartDate><EndDate>2023-6-28</EndDate></ExtEventIdentityCardInfo></ExtEventInfo>�\u0002\u0001�\u0002*http://127.0.0.1:0Failed-to-upload-picture";
        String addressReg = "(?<=Address>)[\\S\\s]*(?=</Address>)";
        String sexReg="(?<=Sex>)[\\S\\s]*(?=</Sex>)";
        String sDateReg="(?<=StartDate>)[\\S\\s]*(?=</StartDate>)";
        String eDateReg="(?<=EndDate>)[\\S\\s]*(?=</EndDate>)";
        String issuingAuthority="(?<=IssuingAuthority>)[\\S\\s]*(?=</IssuingAuthority>)";
        String birth="(?<=Birth>)[\\S\\s]*(?=</Birth>)";
        String IdNum="(?<=IdNum>)[\\S\\s]*(?=</IdNum>)";
        System.out.println(RegUtil.getFirstMatchContent(data, addressReg));
        System.out.println(RegUtil.getFirstMatchContent(data, sexReg));
        System.out.println(RegUtil.getFirstMatchContent(data, sDateReg));
        System.out.println(RegUtil.getFirstMatchContent(data, eDateReg));
        System.out.println(RegUtil.getFirstMatchContent(data, issuingAuthority));
        System.out.println(RegUtil.getFirstMatchContent(data, birth));
        System.out.println(RegUtil.getFirstMatchContent(data, IdNum));
    }


} 
