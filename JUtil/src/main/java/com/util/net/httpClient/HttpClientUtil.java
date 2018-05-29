package com.util.net.httpClient;

import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月10日 14:35
 * @description
 * @modified By:
 * @modifued reason:
 */
public class HttpClientUtil {


    public static HttpResponse doFormPost(String url, List<NameValuePair> nameValuePairList) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity formEntity;
        CloseableHttpResponse response = null;
        try {
            formEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            httpPost.setEntity(formEntity);
            response = client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    private static String formatUrl(String url) {
        String tempUrl = null;
        String URI = null;
        if (url.startsWith("http://")) {
            tempUrl = url.substring("http://".length());
            int index = tempUrl.indexOf("/");
            URI = tempUrl.substring(index);
            String ipPort = tempUrl.substring(0, index);
            String[] ipPortSplitArray = ipPort.split(":");
            if (ipPortSplitArray.length <= 1) {
                return "http://" + ipPort + URI;
            } else {
                if ("80".equals(ipPortSplitArray[1])) {
                    ipPort = ipPort.replace(":80", "");
                }

                return "http://" + ipPort + URI;
            }
        }
        if (url.startsWith("https://")) {
            tempUrl = url.substring("https://".length());
            int index = tempUrl.indexOf("/");
            URI = tempUrl.substring(index);
            String ipPort = tempUrl.substring(0, index);
            String[] ipPortSplitArray = ipPort.split(":");
            if (ipPortSplitArray.length <= 1) {
                return "https://" + ipPort + URI;
            } else {
                if ("443".equals(ipPortSplitArray[1])) {
                    ipPort = ipPort.replace(":443", "");
                }
                return "https://" + ipPort + URI;
            }
        }
        return url;
    }

    public static void main(String[] args) {
        List<NameValuePair> nameValuePairList = Lists.newArrayList();
        NameValuePair p1 = new BasicNameValuePair("unitIdArr", "'1'");
        NameValuePair p2 = new BasicNameValuePair("unitIdArr", "'2'");
        NameValuePair p3 = new BasicNameValuePair("deviceIdArr", "'3'");
        NameValuePair p4 = new BasicNameValuePair("deviceIdArr", "'4'");
        nameValuePairList.add(p1);
        nameValuePairList.add(p2);
        nameValuePairList.add(p3);
        nameValuePairList.add(p4);
        doFormPost("http://127.0.0.1/visAuthService/v1/orgAuths/save", nameValuePairList);
    }


}
