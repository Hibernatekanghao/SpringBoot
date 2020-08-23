package com.kh.helloworld.Provider;


import com.alibaba.fastjson.JSON;
import com.kh.helloworld.dto.AccessTokenDTO;
import com.kh.helloworld.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//当前类初始化到spring 上下文，不需要实例化对象
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));

        Request request = new Request.Builder()
                //步骤3：应用程序轮询GitHub，以检查用户是否授权了该设备
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        System.out.println("==>request: " + request);

        try (Response response = client.newCall(request).execute()) {
            // 尝试将返回值转换成字符串并返回
            String str = response.body().string();
            //用&分割返回的字符串，取出access_token
            String accessToken = str.split("&")[0].split("=")[1];
            System.out.println("==>httpMethodOne 方式二请求返回结果accessToken: " + accessToken);
            return accessToken;
        } catch (IOException e) {
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //步骤3：应用程序轮询GitHub，以检查用户是否授权了该设备
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            // 尝试将返回值转换成字符串并返回
            String string = response.body().string();
            // string 自动转换成java 类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
