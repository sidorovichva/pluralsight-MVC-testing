package com.vs.pluralsightmvctesting.logging.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Youtube
 * Green Learner
 * #10.1 Centralized Logging of All the Endpoints | Microservices Demo with Spring Boot
 * https://www.youtube.com/watch?v=430hP6HesDA
 */
@Slf4j
@Component
@Order(1)
public class RequestResponseLoggers implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //MyCustomHttpRequestWrapper httpServletRequest = new MyCustomHttpRequestWrapper((HttpServletRequest) servletRequest);

        byte[] byteArray = httpServletRequest.getInputStream().readAllBytes();

        log.info("Request URI: {}", httpServletRequest.getRequestURI());
        log.info("Request Method: {}", httpServletRequest.getMethod());
        log.info("Request Body: {}", new String(byteArray));
        //log.info("Request Body: {}", new String(httpServletRequest.getByteArray()));

        filterChain.doFilter(servletRequest, servletResponse);
        //filterChain.doFilter(httpServletRequest, servletResponse);

        //HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    }
}
