package com.example.next_link.service;

import com.example.next_link.entity.Canal;
import com.example.next_link.repository.CanalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CanalService {

    private final CanalRepository canalRepository;

    public List<Canal> getAllCanal(){

        return canalRepository.findAll();
    }

    public Optional<Canal> findById(Integer id){

        return canalRepository.findById(id);}
}
