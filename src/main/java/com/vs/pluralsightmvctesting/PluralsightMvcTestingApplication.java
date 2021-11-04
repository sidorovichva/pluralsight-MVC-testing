package com.vs.pluralsightmvctesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PluralsightMvcTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PluralsightMvcTestingApplication.class, args);
    }

}
