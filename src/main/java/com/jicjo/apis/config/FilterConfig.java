package com.jicjo.apis.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public SecurityHeadersFilter securityFilter() { // Renamed the method slightly
        return new SecurityHeadersFilter();
    }

    @Bean
    public FilterRegistrationBean<SecurityHeadersFilter> securityHeadersFilter() {
        FilterRegistrationBean<SecurityHeadersFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecurityHeadersFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all endpoints
        registrationBean.setOrder(1); // High precedence
        return registrationBean;
    }
}