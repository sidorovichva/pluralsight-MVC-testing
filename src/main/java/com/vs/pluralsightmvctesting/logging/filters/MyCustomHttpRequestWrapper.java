package com.vs.pluralsightmvctesting.logging.filters;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.server.DelegatingServerHttpResponse;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Youtube
 * Green Learner
 * #10.1 Centralized Logging of All the Endpoints | Microservices Demo with Spring Boot
 * https://www.youtube.com/watch?v=430hP6HesDA
 */
public class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper {

    private byte[] byteArray;

    public MyCustomHttpRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            byteArray = request.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Issue while reading request stream");
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
    }

    public byte[] getByteArray() {
        return byteArray;
    }
}
