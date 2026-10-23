package com.example.next_link;

import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Link;
import com.example.next_link.entity.UE;
import com.example.next_link.repository.CanalRepository;
import com.example.next_link.repository.LinkRepository;
import com.example.next_link.repository.UeRepository;
import org.springframework.boot.CommandLineRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DataRunner implements CommandLineRunner {

    private final CanalRepository canalRepository;
    private final LinkRepository linkRepository;
    private final UeRepository ueRepository;

    @Override
    public void run(String... args) throws Exception {

        Canal c1 = new Canal();
        c1.setName("chaineMSP1");

        Canal c2 = new Canal();
        c2.setName("chaineMSP2");

        Canal c3 = new Canal();
        c3.setName("chaineMSP3");

        Canal c4 = new Canal();
        c4.setName("chaineMSP4");

        canalRepository.save(c1);
        canalRepository.save(c2);
        canalRepository.save(c3);
        canalRepository.save(c4);

        UE ue1 = new UE();
        ue1.setName("Algèbre Générale");

        UE ue2 = new UE();
        ue2.setName("Géométrie Vectorielle");

        UE ue3 = new UE();
        ue3.setName("Electomagnétisme");

        UE ue4 = new UE();
        ue4.setName("Electrocinétique");

        UE ue5 = new UE();
        ue5.setName("Mécanique");

        ueRepository.save(ue1);
        ueRepository.save(ue2);
        ueRepository.save(ue3);
        ueRepository.save(ue4);
        ueRepository.save(ue5);


        createLink(UUID.fromString("24f21415-5c73-49be-873e-6f4074b5a8c2"), "video1" , "https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share",c1 );
        createLink(UUID.fromString("4aa89939-d658-4c6a-8bf3-3456b0e2cbd3"), "video2" , "https://www.youtube.com/shorts/N0V85fTvuQU?feature=share",c2 );
        createLink(UUID.fromString("a2153504-0fed-450e-a492-0b33a3f5db60"), "video3" , "https://www.youtube.com/shorts/x18rPV6awok?feature=share",c3 );

    }

    private void createLink(UUID shortCode, String title, String url, Canal canal) {
        Link link = new Link();
        link.setShortCode(shortCode);
        link.setTitle(title);
        link.setUrl(url);
        link.setCanal(canal);
        link.setPublicationDate(LocalDateTime.now());

        linkRepository.save(link);
    }
}
