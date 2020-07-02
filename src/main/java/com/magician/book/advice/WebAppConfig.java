package com.magician.book.advice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    //实现拦截器 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径

        registry.addInterceptor(new InterceptorConfig())
               .addPathPatterns("/origin/admin/index.html",
                        "/origin/admin/html/*",
                        "/admin/*",
                       "/origin/writer/index.html");
    }
}
