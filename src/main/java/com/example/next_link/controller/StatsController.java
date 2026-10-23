package com.example.next_link.controller;
import com.example.next_link.dto.LinkStat;
import com.example.next_link.dto.PersonStat;
import com.example.next_link.service.ClicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class StatsController {

    private final ClicService clicService;

    @GetMapping("/personnes")
    public ModelAndView getPersonsStats (){

        List<PersonStat> personStats = clicService.getPersonsStats();

        return new ModelAndView("personsStats")
                .addObject("personsStats",
                        personStats);
    }

    @GetMapping("/liens")
    public ModelAndView getLinksStats (){

        List<LinkStat> linksStats = clicService.getLinksStats();

        return new ModelAndView("linksStats")
                .addObject("linksStats",
                        linksStats);
    }


}
