package com.example.next_link.controller;
import com.example.next_link.dto.ClicStat;
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

    @GetMapping("/stats")
    public ModelAndView getStats (){

        List<ClicStat> statsClics = clicService.getClicStats();
        System.out.println(statsClics);

        return new ModelAndView("formStatClics")
                .addObject("stats",
                        statsClics);
    }
}
