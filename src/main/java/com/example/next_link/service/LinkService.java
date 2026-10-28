package com.example.next_link.service;

import com.example.next_link.dto.LinkForm;
import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Link;
import com.example.next_link.entity.UE;
import com.example.next_link.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

        if (ue.isPresent() && canal.isPresent()){

            String shortCode =  generateUniqueCode();

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

    private String generateUniqueCode(){

        String shortCode;

        for ( int i = 0; i <= 20;  i++){

            shortCode = shortCodeGenerator.generate();

            if(!linkRepository.existsByShortCode(shortCode)){

                return shortCode;
            }

        }
        throw new IllegalStateException(
                "Impossible de générer un code unique après 20 essais");
    }






}
