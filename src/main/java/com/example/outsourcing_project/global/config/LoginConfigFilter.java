package com.example.outsourcing_project.global.config;


import com.example.outsourcing_project.global.filter.LoginJwtFilter;
import com.example.outsourcing_project.global.util.LoginJwtUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfigFilter {

    @Bean
    public FilterRegistrationBean<LoginJwtFilter> jwtFilter(LoginJwtUtil loginJwtUtil) {
        FilterRegistrationBean<LoginJwtFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginJwtFilter(loginJwtUtil));
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
