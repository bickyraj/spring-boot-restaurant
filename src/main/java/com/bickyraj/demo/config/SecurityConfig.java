package com.bickyraj.demo.config;

import com.bickyraj.demo.filter.ClientPermissionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnBean(ClientPermissionFilter.class)
public class SecurityConfig {
    private final ClientPermissionFilter clientPermissionFilter;

    @Bean
    public FilterRegistrationBean<ClientPermissionFilter> clientPermissionFilterRegistration() {
        FilterRegistrationBean<ClientPermissionFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(clientPermissionFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
