package com.example.next_link.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomShortCodeGenerator  implements ShortCodeGenerator{

    private static final String ALPHABET = "abcdefghjkmnpqrstuvwxyz23456789";
    private static final int LENGTH = 4;
    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }


}
