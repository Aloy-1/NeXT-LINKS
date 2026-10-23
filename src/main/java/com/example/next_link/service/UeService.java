package com.example.next_link.service;
import com.example.next_link.entity.UE;
import com.example.next_link.repository.UeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UeService {

    private final UeRepository ueRepository;

    public List<UE> getAllUe(){

        return ueRepository.findAll();
    }


    public Optional<UE> findById(Integer id){

        return ueRepository.findById(id);
    }
}
