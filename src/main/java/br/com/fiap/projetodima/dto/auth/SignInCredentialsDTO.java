package br.com.fiap.projetodima.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInCredentialsDTO(
        @Email
        @NotBlank @Size(min = 5, max = 320)
        String email,

        @NotBlank @Size(min = 8)
        String password
) {
}
