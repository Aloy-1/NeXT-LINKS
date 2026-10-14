package com.example.next_link;


import com.example.next_link.controller.RedirectController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RedirectController.class)
public class RedirectControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenExisting_whenGetRequest_thenRedirectToYoutube()
            throws Exception {

        mvc.perform(get("/r?canal=msp1&videoId=1"))
                .andExpect(status().isFound())
                .andExpect(header().string("location","https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share" ) );

    }

    @Test
    public void givenNotExistingId_whenGetRequest_thenRaiseNotFoundError()
            throws Exception {

        mvc.perform(get("/r?canal=msp1&videoId=100"))
                .andExpect(status().isNotFound());

    }
}
