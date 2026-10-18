package com.example.next_link.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegistrationForm(@NotBlank
                               String canal,

                               @NotNull
                               Integer videoId,

                               @NotBlank(message = "Vous devez entrer votre nom")
                               String lastName,

                               @NotBlank(message = "Vous devez entrer votre prénom")
                               String firstName,

                               @NotBlank @Email(message = "Adresse courriel invalide")
                               String email,

                               @NotBlank
                               String phoneNumber) {
}
