package com.example.next_link.repository;

import com.example.next_link.dto.ClicStat;
import com.example.next_link.entity.Clic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ClicRepository extends JpaRepository<Clic, Integer> {

    @Query("SELECT new com.example.next_link.dto.ClicStat( c.video.link ,c.canal.name , COUNT(c) ) " +
            "FROM Clic c " +
            "JOIN c.video " +
            "JOIN c.canal " +
            "GROUP BY c.video.link, c.canal.name ")
    List<ClicStat> countClicsByCanalAndVideo();
}
