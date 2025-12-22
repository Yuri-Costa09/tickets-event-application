package com.yuricosta.template_spring_boot.shared.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
