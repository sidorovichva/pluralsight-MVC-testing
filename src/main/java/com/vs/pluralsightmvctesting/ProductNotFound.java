package com.vs.pluralsightmvctesting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //or use @ExceptionHandler in Controller
public class ProductNotFound extends RuntimeException{
}
