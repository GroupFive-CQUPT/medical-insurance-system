package com.thefive.apiserver.config;

import com.thefive.apiserver.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    /**
     * 配置Token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns(    // 排除不需要拦截的路径
                        "/user/login",           // 登录接口
                        "/user/register",        // 注册接口
                        "/",                     // 根路径
                        "/doc.html",             // swagger-bootstrap-ui 的路径
                        "/swagger-ui.html",      // swagger 官方 ui 的路径
                        "/swagger-resources/**", // swagger 资源
                        "/v2/api-docs",          // swagger 核心 json 文件
                        "/webjars/**",           // swagger 静态资源
                        "/static/**",            // 静态资源
                        "/public/**"             // 公共资源
                );
    }

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}