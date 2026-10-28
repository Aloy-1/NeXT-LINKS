package com.example.next_link.dto;

import com.example.next_link.entity.Promotion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record RegistrationForm(@NotBlank( message = "SVP, Vous devez entrer votre nom")
                               String fullName ,

                               @NotBlank( message = "Vous avez falsifié le formulaire")
                               String shortCode,

                               @NotNull(message = "SVP, Vous devez mentionner votre niveau")
                               Promotion promotion) {
}
