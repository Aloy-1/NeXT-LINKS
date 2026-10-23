package com.example.next_link.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public record LinkStat(UUID shortCode , String title, String ue , LocalDateTime publicationDate , Long visits , Long namedVisits , boolean active) {
}
