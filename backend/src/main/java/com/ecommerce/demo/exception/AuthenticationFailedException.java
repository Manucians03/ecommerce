package com.ecommerce.demo.exception;

public class AuthenticationFailedException extends IllegalArgumentException{
    public AuthenticationFailedException(String s) {
        super(s);
    }
}
