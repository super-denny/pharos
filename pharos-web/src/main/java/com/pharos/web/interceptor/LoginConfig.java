package com.pharos.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wcj
 * @ClassName LoginConfig
 * @Description 拦截器配置
 * @createTime 2021-09-12
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/api/**");
        registration.excludePathPatterns("/api/login/**");

        InterceptorRegistration adminRegistration = registry.addInterceptor(new AdminLoginInterceptor());
        adminRegistration.addPathPatterns("/admin/**");
        adminRegistration.excludePathPatterns("/admin/login/**");
    }
}
