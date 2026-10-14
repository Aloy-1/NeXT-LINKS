package com.example.next_link.repository;

import com.example.next_link.entity.Canal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanalRepository extends JpaRepository<Canal, Integer> {

    Optional<Canal> findByName(String name);

}
