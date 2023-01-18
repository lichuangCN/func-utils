package com.example.func.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author lichuang
 * @date 2022/05/16
 */
public class RestTemplateUtil {

    /**
     * RestTemplate 请求
     * form-data
     */
    public static String post(String url, Map<String, Object> data) {
        // 参数
        MultiValueMap<String, Object> params = new LinkedMultiValueMap();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            params.add(entry.getKey(), entry.getValue());
        }

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用
        ResponseEntity<Object> response = client.exchange(url, method, requestEntity, Object.class);
        return JSONObject.toJSONString(response.getBody());
    }

}
