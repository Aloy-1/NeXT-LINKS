package com.example.next_link.service;

import com.example.next_link.dto.LinkStat;
import com.example.next_link.dto.PersonStat;
import com.example.next_link.dto.RegistrationForm;

import com.example.next_link.entity.Clic;
import com.example.next_link.entity.Person;
import com.example.next_link.entity.Link;
import com.example.next_link.repository.CanalRepository;
import com.example.next_link.repository.ClicRepository;
import com.example.next_link.repository.PersonRepository;
import com.example.next_link.repository.LinkRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClicService {

    private final ClicRepository clicRepository;
    private final CanalRepository canalRepository;
    private final LinkRepository linkRepository;
    private final PersonRepository personRepository;
    private final ShortCodeGenerator shortCodeGenerator;


    public void registerClic( Link link, Person person){

        LocalDateTime dateTime = LocalDateTime.now();
        Clic clic = new Clic();
        clic.setLink(link);
        clic.setDateTime(dateTime);
        clic.setPerson(person);
        clicRepository.save(clic);
    }

    public void registerAnonymousClic(Link link){

        LocalDateTime dateTime = LocalDateTime.now();
        Clic clic = new Clic();
        clic.setLink(link);
        clic.setDateTime(dateTime);
        clicRepository.save(clic);
    }



    public Optional<String> handleClic(String canalName, Integer videoId  ){

//        Optional<Video> video = videoRepository.findById(videoId);
//
//        Optional<Canal> canal = canalRepository.findByName(canalName);
//
//        if (video.isEmpty() || canal.isEmpty()){
//
//            return Optional.empty();
//        }
//
//        this.registerClic(canal.get(), video.get() , person);

//        return Optional.of(video.get().getLink());

        return  null;

    }

    public Optional<Link> resolveLink(UUID shortCode){

        if(shortCode == null){

            return Optional.empty();
        }

        return linkRepository.findByShortCodeAndActiveTrue(shortCode);

    }


    public Optional<Person> resolvePerson(String visitorId){


        Optional<UUID> uuid = convertToUUID(visitorId);

       if (uuid.isPresent()) {

           return personRepository.findByVisitorId(uuid.get());

       }else {
           return Optional.empty();
       }
    }

    public Person createPerson(RegistrationForm registrationForm){

        Person newPerson = new Person();
        UUID visitorId = UUID.randomUUID();

        newPerson.setFullName(registrationForm.fullName());
        newPerson.setPromotion(registrationForm.promotion());
        newPerson.setVisitorId(visitorId);

        return personRepository.save(newPerson);

    }

    public List<PersonStat> getPersonsStats(){

        return clicRepository.listOfPersonsStats();
    }

    public List<LinkStat> getLinksStats(){

        return linkRepository.listOfLinksStats();
    }

    private Optional<UUID> convertToUUID(String word){

        if(word != null && !word.isBlank() ){

            UUID uuid;

            try {
                uuid = UUID.fromString(word);

            } catch (IllegalArgumentException e) {

                log.warn(" Cookie visitor invalid : {}", word);

                return Optional.empty();
            }

            return  Optional.of(uuid);
        }

        return Optional.empty();

    }







}
