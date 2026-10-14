package com.example.next_link.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class RedirectController {

    final Map<Integer, String> videoUrls = Map.of(  1,"https://www.youtube.com/shorts/C-GZdIT1_I0?feature=share",
                                                2,"https://www.youtube.com/shorts/N0V85fTvuQU?feature=share",
                                                3,"https://www.youtube.com/shorts/x18rPV6awok?feature=share");
    @GetMapping("/r")
    public ResponseEntity<Void>redirect (@RequestParam("videoId") Integer requestVideo , @RequestParam("canal") String requestCanal) throws URISyntaxException {

        if (videoUrls.containsKey(requestVideo)){

            String link = videoUrls.get(requestVideo);

            URI location = new URI(link);

            HttpHeaders responseHeaders = new HttpHeaders();

            responseHeaders.setLocation(location);

            return new ResponseEntity<Void>(responseHeaders, HttpStatusCode.valueOf(302));

        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
