package com.example.next_link.repository;


import com.example.next_link.dto.PersonStat;
import com.example.next_link.entity.Clic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ClicRepository extends JpaRepository<Clic, Integer> {

    @Query("SELECT new com.example.next_link.dto.PersonStat(p.id, p.fullName ,p.promotion , p.createdAt, Max(c.dateTime) ) " +
            "FROM Clic c " +
            "JOIN c.person p " +
            "GROUP BY p.id " +
            "ORDER BY Max(c.dateTime) DESC ")
    List<PersonStat> listOfPersonsStats();
}
