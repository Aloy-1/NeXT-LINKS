package com.example.next_link.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShortCodeGenerator {

    public UUID generate(){

        return UUID.randomUUID();
    }
}
