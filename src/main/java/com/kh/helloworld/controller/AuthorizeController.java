package com.kh.helloworld.controller;


import com.kh.helloworld.Provider.GithubProvider;
import com.kh.helloworld.dto.AccessTokenDTO;
import com.kh.helloworld.dto.GithubUser;
import com.kh.helloworld.mapper.UserMapper;
import com.kh.helloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller

public class AuthorizeController {
    //构造器注入
    private GithubProvider githubProvider;

    public AuthorizeController(GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }

    // github.Client_id=7e5d84198f969a4ab9f9
    //github.Client_secret=a3fb6d531f38639571d517bd800abc70bbc24db3
    //github.Redirect_uri=http://localhost:8774/callback
    //注入application.properties 变量
    @Value("${github.Client_id}")
    private String Client_id;
    @Value("${github.Client_secret}")
    private String Client_secret;
    @Value("${github.Redirect_uri}")
    private String Redirect_uri;
    //set方法注入
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        // HttpServletRequest  自动加载前端请求的上下文
        //获取返回的参数  调用provider 方法  shift + enter 重启一行
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        System.out.println("==>httpMethodOne 方式一请求返回结果Client_id: " + code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubuser = githubProvider.getUser(accessToken);

        if (githubuser != null) {
            User user = new User();

            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubuser.getName());
            user.setAccount_id(String.valueOf(githubuser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            //登陆成功把token 放到cookie 中

            response.addCookie(new Cookie("token", token));

            //登录成功 写cookie  和session
            //request.getSession().setAttribute("user",githubuser);
            //自动跳转到 redirect返回的是路径
        } else {
            //登录失败,重新登录
            return "redirect:/";
        }
        return "redirect:/";
        // System.out.println("==>httpMethodOne 方式三请求返回结果: " + username);
        //System.out.println("==>httpMethodOne 方式四请求返回结果: " + user.getId());
        //System.out.println("==>httpMethodOne 方式五请求返回结果: " + senior);
    }
}
