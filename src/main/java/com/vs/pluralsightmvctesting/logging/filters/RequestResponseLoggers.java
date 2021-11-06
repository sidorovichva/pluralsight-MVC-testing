package com.vs.pluralsightmvctesting.logging.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vs.pluralsightmvctesting.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * https://www.baeldung.com/spring-reading-httpservletrequest-multiple-times
 * https://www.youtube.com/watch?v=430hP6HesDA
 */
@Slf4j
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "ContentCachingFilter", urlPatterns = "/*")
public class RequestResponseLoggers implements Filter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        CachedBodyHttpServletRequest httpServletRequest = new CachedBodyHttpServletRequest((HttpServletRequest) servletRequest);

        log.info("Request URI: {}", httpServletRequest.getRequestURI());
        log.info("Request Method: {}", httpServletRequest.getMethod());

        byte[] byteArray = httpServletRequest.getInputStream().readAllBytes();
        String requestBody = new String(byteArray);
        Product product = objectMapper.readValue(requestBody, Product.class);
        product.setName("***");
        requestBody = objectMapper.writeValueAsString(product);
        log.info("Request Body: {}", requestBody);

        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
