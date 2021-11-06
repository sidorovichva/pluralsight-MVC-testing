package com.vs.pluralsightmvctesting.logging.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class FiltersConfig {

    @Bean
    FilterRegistrationBean<RequestResponseLoggers> createLoggers(RequestResponseLoggers requestResponseLoggers) {
        FilterRegistrationBean<RequestResponseLoggers> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(requestResponseLoggers);

        registrationBean.addUrlPatterns("/products");

        return registrationBean;
    }
}
