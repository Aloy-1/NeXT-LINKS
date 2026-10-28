package com.example.next_link.repository;

import com.example.next_link.dto.LinkStat;
import com.example.next_link.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface LinkRepository extends JpaRepository<Link, Integer> {

    Optional<Link> findByShortCodeAndActiveTrue(String shortCode);

    boolean existsByShortCode(String shortCode);

    @Query("SELECT new com.example.next_link.dto.LinkStat(l.shortCode, l.title , u.name , l.publicationDate, Count(c.link), Count(c.person) ,l.active  ) " +
            "FROM Link l " +
            "LEFT JOIN Clic c ON c.link = l " +
            "LEFT JOIN l.ue u " +
            "GROUP BY l.id , u.name " +
            "ORDER BY l.publicationDate DESC"
          )
    List<LinkStat> listOfLinksStats();

}
