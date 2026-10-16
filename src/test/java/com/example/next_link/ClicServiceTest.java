package com.example.next_link;

import com.example.next_link.entity.Canal;
import com.example.next_link.entity.Clic;
import com.example.next_link.entity.Video;
import com.example.next_link.repository.CanalRepository;
import com.example.next_link.repository.ClicRepository;
import com.example.next_link.repository.VideoRepository;
import com.example.next_link.service.ClicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClicServiceTest {

    @InjectMocks
    private ClicService clicService;

    @Mock
    private ClicRepository clicRepository;

    @Mock
    private  CanalRepository canalRepository;

    @Mock
    private  VideoRepository videoRepository;

    private  Canal canal;

    private Video video;

    private Clic clic;

    @Captor
    ArgumentCaptor<Clic> clicCaptor;
    @BeforeEach
    void setUp() {
         canal = new Canal();
         canal.setName("chaineMSP1");

         video = new Video();
         video.setLink("https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share");
         video.setId(1);



    }

    @Test
    @DisplayName("Should create a new clic")
    void  givenCanalAndVideo_whenRegisterClic_thenSaveClic(){

        clicService.registerClic(canal, video);

        Mockito.verify(clicRepository).save(clicCaptor.capture());

        Clic clicCaptorValue = clicCaptor.getValue();

        assertEquals(canal, clicCaptorValue.getCanal());
        assertEquals(video, clicCaptorValue.getVideo());
        assertNotNull(clicCaptorValue.getDateTime());
    }

    @Test
    @DisplayName("Should return and empty optional when bad videoId supply")
    void givenNoVideoId_whenHandleClic_thenReturnEmptyOptional(){

        Integer unknownVideoId = 99;

        when(canalRepository.findByName(canal.getName())).thenReturn(Optional.of(canal));
        when(videoRepository.findById( unknownVideoId)).thenReturn(Optional.empty());

        java.util.Optional<String> clic = clicService.handleClic(canal.getName(), unknownVideoId);

        assertEquals(Optional.empty(),clic );
        Mockito.verify(clicRepository, never()).save(any());

    }

    @Test
    @DisplayName("Should return and empty optional when none valid link Name was supply")
    void givenNonValidCanalName_whenHandleClic_thenReturnEmptyOptional(){

        String unknownCanal = "fuck you";


        when(canalRepository.findByName(unknownCanal)).thenReturn(Optional.empty());
        when(videoRepository.findById(video.getId())).thenReturn(Optional.of(video));

        java.util.Optional<String> clic = clicService.handleClic(unknownCanal, video.getId());

        assertEquals(Optional.empty(),clic );
        Mockito.verify(clicRepository, never()).save(any());

    }





}
