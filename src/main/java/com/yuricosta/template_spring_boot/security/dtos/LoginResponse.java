package com.yuricosta.template_spring_boot.security.dtos;

public record LoginResponse(
        String token,
        Long expiresIn
) {
}
