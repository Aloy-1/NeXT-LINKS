package com.example.next_link.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "visitorId" , nullable = false , unique = true)
    private java.util.UUID visitorId;

}
