package com.cslg.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //重写登录拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行所有接口
    }
}
