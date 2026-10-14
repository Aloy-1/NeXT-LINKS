package com.example.next_link.service;

import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Clic;
import com.example.next_link.entity.Video;
import com.example.next_link.repository.CanalRepository;
import com.example.next_link.repository.ClicRepository;
import com.example.next_link.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClicService {

    private final ClicRepository clicRepository;
    private final CanalRepository canalRepository;
    private final VideoRepository videoRepository;

    public void registerClic(Canal canal, Video video){

        LocalDateTime dateTime = LocalDateTime.now();

        Clic clic = new Clic();
        clic.setCanal(canal);
        clic.setVideo(video);
        clic.setDateTime(dateTime);

        clicRepository.save(clic);

    }

    public Optional<String> handleClic(String canalName, Integer videoId){

        Optional<Video> video = videoRepository.findById(videoId);

        Optional<Canal> canal = canalRepository.findByName(canalName);

        if (video.isEmpty() || canal.isEmpty()){

            return Optional.empty();
        }

        this.registerClic(canal.get(), video.get());

        return Optional.of(video.get().getLink());

    }


}
