package com.example.next_link;

import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Video;
import com.example.next_link.repository.CanalRepository;
import com.example.next_link.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class NeXtLinkApplication implements CommandLineRunner {

    private final CanalRepository canalRepository;
    private final VideoRepository videoRepository;

    public static void main(String[] args) {
        SpringApplication.run(NeXtLinkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Canal c1 = new Canal();
        c1.setName("chaineMSP1");

        Canal c2 = new Canal();
        c2.setName("chaineMSP2");

        Canal c3 = new Canal();
        c3.setName("chaineMSP3");

        Canal c4 = new Canal();
        c4 .setName("chaineMSP4");

        canalRepository.save(c1);
        canalRepository.save(c2);
        canalRepository.save(c3);
        canalRepository.save(c4);

        Video v1 = new Video();
        v1.setLink("https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share");

        Video v2 = new Video();
        v2.setLink("https://www.youtube.com/shorts/N0V85fTvuQU?feature=share");

        Video v3 = new Video();
        v3.setLink("https://www.youtube.com/shorts/x18rPV6awok?feature=share");

        videoRepository.save(v1);
        videoRepository.save(v2);
        videoRepository.save(v3);

        System.out.println("Hello World from CommandLineRunner, Application Started!!");
    }

}
