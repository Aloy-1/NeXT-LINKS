package com.example.next_link.dto;


import java.time.LocalDateTime;


public record LinkStat(String shortCode , String title, String ue , LocalDateTime publicationDate , Long visits , Long namedVisits , boolean active) {
}
