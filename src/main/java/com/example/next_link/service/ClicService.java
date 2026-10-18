package com.example.next_link.service;

import com.example.next_link.dto.RegistrationForm;
import com.example.next_link.dto.ResolvedLink;
import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Clic;
import com.example.next_link.entity.Person;
import com.example.next_link.entity.Video;
import com.example.next_link.repository.CanalRepository;
import com.example.next_link.repository.ClicRepository;
import com.example.next_link.repository.PersonRepository;
import com.example.next_link.repository.VideoRepository;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
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
    private final VideoRepository videoRepository;
    private final PersonRepository personRepository;

    public void registerClic(Canal canal, Video video, Person person){

        LocalDateTime dateTime = LocalDateTime.now();

        Clic clic = new Clic();
        clic.setCanal(canal);
        clic.setVideo(video);
        clic.setDateTime(dateTime);
        clic.setPerson(person);

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

    public Optional<ResolvedLink> resolveLink(String canalName, Integer videoId){

        Optional<Video> video = videoRepository.findById(videoId);

        Optional<Canal> canal = canalRepository.findByName(canalName);

        if (video.isEmpty() || canal.isEmpty()){

            return Optional.empty();
        }

        return Optional.of(new ResolvedLink(video.get(), canal.get()));
    }

    public Optional<Person> resolvePerson(String visitorId){


        Optional<UUID> uuid = convertToUUID(visitorId);

       if (uuid.isPresent()) {

           return personRepository.findByVisitorId(uuid.get());

       }else {
           return Optional.empty();
       }
    }

    public Person findOrCreatePerson(RegistrationForm registrationForm){

        Optional<Person> person = personRepository.findByEmail(registrationForm.email());

        if (person.isPresent())
            return person.get();

        else{
            Person newPerson = new Person();
            UUID visitorId = UUID.randomUUID();

            newPerson.setEmail(registrationForm.email());
            newPerson.setFirstName(registrationForm.firstName());
            newPerson.setLastName(registrationForm.lastName());
            newPerson.setPhoneNumber(registrationForm.phoneNumber());
            newPerson.setVisitorId(visitorId);
            return personRepository.save(newPerson);
        }


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
