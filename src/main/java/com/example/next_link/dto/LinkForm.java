package com.example.next_link.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record LinkForm(
                            @NotBlank(message = "Vous devez spécifier l'url")
                            @URL(message = "Vous devez spécifier une url valide")
                            String url,

                            @NotBlank(message = "Vous devez spécifier obligatoirement un titre à un lien")
                            String title,

                            @NotNull(message = "l'UE est obligatoire")
                            Integer ueId,

                            @NotNull(message = "le canal est obligatoire")
                            Integer canalId ,

                            @NotNull(message = "les formats de la date et l'heure de publication ne sont pas valides.")
                            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
                            LocalDateTime publicationDate) {
}


