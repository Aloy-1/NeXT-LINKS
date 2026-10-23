package com.example.next_link.repository;

import com.example.next_link.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByVisitorId(UUID visitorId);

}
