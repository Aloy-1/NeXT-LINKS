package com.example.next_link.service;

import com.example.next_link.dto.LinkForm;
import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Link;
import com.example.next_link.entity.UE;
import com.example.next_link.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LinkService {


    private final CanalService canalService;
    private final UeService ueService;
    private final ShortCodeGenerator shortCodeGenerator;
    private final LinkRepository linkRepository;


    public Optional<String> createLink(LinkForm form){


        Optional<UE> ue = ueService.findById( form.ueId());
        Optional<Canal> canal = canalService.findById(form.canalId());

        if (ue.isPresent() && canal.isPresent() ){

            UUID shortCode = shortCodeGenerator.generate();

            Link link = new Link();

            link.setShortCode(shortCode);
            link.setUe(ue.get());
            link.setCanal(canal.get());
            link.setTitle(form.title());
            link.setUrl(form.url());
            link.setPublicationDate(form.publicationDate());

            linkRepository.save(link);

            return Optional.of(shortCode.toString());

        }
        return Optional.empty();




    }








}
