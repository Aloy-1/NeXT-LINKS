package com.example.next_link.repository;

import com.example.next_link.entity.UE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UeRepository extends JpaRepository<UE, Integer> {

    Optional<UE> findById(Integer integer);
}
