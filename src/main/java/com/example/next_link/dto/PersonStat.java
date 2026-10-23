package com.example.next_link.dto;


import com.example.next_link.entity.Promotion;

import java.time.LocalDateTime;

public record PersonStat(Integer id , String fullName, Promotion promotion, LocalDateTime firstVisit, LocalDateTime lastVisit) {


}
