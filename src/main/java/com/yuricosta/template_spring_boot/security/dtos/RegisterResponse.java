package com.yuricosta.template_spring_boot.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterResponse(
        String id,
        @NotEmpty
        String name,
        @Email
        @NotEmpty
        String email
) {
}
