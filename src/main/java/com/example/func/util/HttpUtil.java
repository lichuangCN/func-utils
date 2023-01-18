package com.example.func.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * @author lichuang
 * @date 2021/11/05
 */
@Slf4j
public class HttpUtil {

    public static String post(String url, Object data) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        String json = "";
        try {
            client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            //处理入参
            String dataJson = JSONArray.toJSONString(data);
            StringEntity stringEntity = new StringEntity(dataJson, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return json;
    }

    public static String post(String url, Map<String, Object> data) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        String json = "";
        String params = "";
        try {
            client = HttpClients.createDefault();
            if (CollUtil.isNotEmpty(data)) {
                StringBuilder builder = new StringBuilder();
                builder.append("?");
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    if (ObjectUtil.isNull(entry.getValue())) {
                        continue;
                    }
                    String key = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    builder.append(key).append("=").append(value).append("&");
                }
                String s = builder.toString();
                params = s.substring(0, s.length() - 1);
            }
            String uri = url + params;
            HttpPost httpPost = new HttpPost(uri);
            response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            response.close();
            client.close();
        }
        return json;
    }

    public static String get(String url, Map<String, Object> data) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        String json = "";
        String params = "";
        try {
            client = HttpClients.createDefault();
            if (CollUtil.isNotEmpty(data)) {
                StringBuilder builder = new StringBuilder();
                builder.append("?");
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    if (ObjectUtil.isNull(entry.getValue())) {
                        continue;
                    }
                    String key = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    builder.append(key).append("=").append(value).append("&");
                }
                String s = builder.toString();
                params = s.substring(0, s.length() - 1);
            }
            String uri = url + params;
            HttpGet httpGet = new HttpGet(uri);
            response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
            client.close();
        }
        return json;
    }

}
