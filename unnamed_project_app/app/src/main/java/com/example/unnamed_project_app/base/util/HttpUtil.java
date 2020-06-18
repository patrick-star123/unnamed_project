package com.example.unnamed_project_app.base.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    public static String sendPostHttpRequest(Map<String, String> paramMap, String url){
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if(paramMap != null){
            Set<String> paramKey = paramMap.keySet();
            for (String param : paramKey) {
                builder.add(param, paramMap.get(param));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.body() == null){
                return "";
            }else {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
