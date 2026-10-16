package com.example.next_link;


import com.example.next_link.controller.RedirectController;
import com.example.next_link.service.ClicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RedirectController.class)
public class RedirectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    ClicService clicService;

    @Test
    public void givenExisting_whenGetRequest_thenRedirectToYoutube()

            throws Exception {

        when(clicService.handleClic("chaineMSP1", 1)).thenReturn(Optional.of("https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share"));

        mvc.perform(get("/r?canal=chaineMSP1&videoId=1"))
                .andExpect(status().isFound())
                .andExpect(header().string("location","https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share" ) );

        Mockito.verify(clicService).handleClic("chaineMSP1", 1);
    }

    @Test
    public void givenNotExistingId_whenGetRequest_thenRaiseNotFoundError()
            throws Exception {
        when(clicService.handleClic("chaineMSP1", 100)).thenReturn(Optional.empty());
        mvc.perform(get("/r?canal=chaineMSP1&videoId=100"))
                .andExpect(status().isNotFound());

    }
}
