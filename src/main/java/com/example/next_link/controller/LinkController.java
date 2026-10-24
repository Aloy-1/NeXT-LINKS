package com.example.next_link.controller;

import com.example.next_link.dto.LinkForm;

import com.example.next_link.entity.Canal;
import com.example.next_link.entity.UE;

import com.example.next_link.service.CanalService;

import com.example.next_link.service.LinkService;
import com.example.next_link.service.UeService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class LinkController {

    private final CanalService canalService;
    private final UeService ueService;
    private final LinkService linkService;

    @Value("${APP_BASE_URL}")
    private String NOM_DE_DOMAIN;

    @GetMapping("/liens/nouveau")
    public ModelAndView getFormNewLink( @RequestParam(required = false)  String code ) {

        List<Canal> canalList = canalService.getAllCanal();
        List<UE> ueList = ueService.getAllUe();


        ModelAndView modelAndView = new ModelAndView("formNewLink")
                .addObject("linkForm", new LinkForm(null, null, null, null,
                        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)))
                .addObject("canalList", canalList)
                .addObject("ueList", ueList);

            if (code != null) {
                modelAndView.addObject("newLink", NOM_DE_DOMAIN + "/r/" + code);
            }

            return modelAndView;

    }

    @PostMapping("/liens")
    public ModelAndView createLink(@ModelAttribute("linkForm") @Validated LinkForm linkForm, BindingResult bindingResult ,  HttpServletResponse response){

        List<Canal> canalList = canalService.getAllCanal();
        List<UE> ueList = ueService.getAllUe();
        if (bindingResult.hasErrors()) {

            return new ModelAndView("formNewLink")
                    .addObject("canalList", canalList)
                    .addObject("ueList", ueList );
        }

        Optional<String> shortCode = linkService.createLink(linkForm);

        if(shortCode.isEmpty()){
            response.setStatus(400);
            return new ModelAndView("error/4xx") ;
        }

        return new ModelAndView("redirect:" + "/liens/nouveau?code=" +   shortCode.get());

    }





}
