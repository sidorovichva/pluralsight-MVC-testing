package com.vs.pluralsightmvctesting.logging.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vs.pluralsightmvctesting.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //MyCustomHttpRequestWrapper httpServletRequest = new MyCustomHttpRequestWrapper((HttpServletRequest) servletRequest);

        log.info("Request URI: {}", httpServletRequest.getRequestURI());
        log.info("Request Method: {}", httpServletRequest.getMethod());

        byte[] byteArray = httpServletRequest.getInputStream().readAllBytes();

        String requestBody = new String(byteArray);
        Product product = objectMapper.readValue(requestBody, Product.class);
        product.setName("***");
        requestBody = objectMapper.writeValueAsString(product);

        log.info("Request Body: {}", requestBody);

        //log.info("Request Body: {}", new String(httpServletRequest.getByteArray()));

        //MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletRequest) servletResponse);

        filterChain.doFilter(httpServletRequest, servletResponse);

        //HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    }


}
