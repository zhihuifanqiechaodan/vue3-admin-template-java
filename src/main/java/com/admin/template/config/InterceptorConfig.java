package com.admin.template.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * InterceptorConfig 是一个配置类，用于添加拦截器。
 * 在这个类中，我们可以配置需要拦截的接口路径以及排除不需要拦截的接口路径。
 * 在这个例子中，我们添加了JWTInterceptor拦截器来对请求进行token验证，
 * 并设置了"/user/test"接口需要进行验证，而"/user/login"接口则被排除在验证之外，即所有用户都放行登录接口。
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")              // 对所有的接口进行token验证
                .excludePathPatterns("/system/get_token")
                .excludePathPatterns("/system/validate_token")
                .excludePathPatterns("/system/login");  // 放行登录接口
    }
}
