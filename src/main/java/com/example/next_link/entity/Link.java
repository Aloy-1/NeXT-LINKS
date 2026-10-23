package com.example.next_link.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "short_code" , nullable = false , unique = true)
    private UUID shortCode;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "url" , nullable = false)
    private String url;

    @ManyToOne
    private UE ue;

    @ManyToOne
    private Canal canal;

    @Column(name = "publicationDate" , nullable = false)
    private LocalDateTime publicationDate;

    @Column(name = "active" , nullable = false)
    private boolean active = true;

}
