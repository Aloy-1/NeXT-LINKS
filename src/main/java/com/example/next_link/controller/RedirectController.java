package com.example.next_link.controller;

import com.example.next_link.dto.RegistrationForm;
import com.example.next_link.entity.Link;
import com.example.next_link.entity.Person;
import com.example.next_link.service.ClicService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;


import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class RedirectController {

    private final ClicService clicService;
    static final String VISITOR_COOKIE = "visitorId";

    @GetMapping("/r/{shortCode}")
    public ModelAndView redirect(@PathVariable String shortCode, @CookieValue(name = VISITOR_COOKIE, required = false) String visitorId) {

        Optional<Link> resolvedLink = clicService.resolveLink(shortCode);

        if (resolvedLink.isPresent())
        {

            Optional<Person> person = clicService.resolvePerson(visitorId);

            if (person.isPresent())
            {
                clicService.registerClic(resolvedLink.get(), person.get());

                return new ModelAndView("redirect:" + resolvedLink.get().getUrl());

            } else
            {

                return new ModelAndView("form")
                        .addObject("formInfosUser",
                                new RegistrationForm(null, resolvedLink.get().getShortCode(), null));
            }

        } else {

            return errorView();
        }
    }


    @PostMapping("/register")
    public ModelAndView registerPersonClic(@ModelAttribute("formInfosUser") @Validated RegistrationForm registrationForm, BindingResult bindingResult, @CookieValue(name = VISITOR_COOKIE, required = false) String visitorId , HttpServletResponse response) {

        Optional<Link> resolvedLink = clicService.resolveLink(registrationForm.shortCode());

        if (resolvedLink.isEmpty()) {

            return errorView();
        }

        Optional<Person> person = clicService.resolvePerson(visitorId);

        if (person.isPresent())
        {
            clicService.registerClic(resolvedLink.get(), person.get());

            return new ModelAndView("redirect:" + resolvedLink.get().getUrl());

        }

        if (bindingResult.hasErrors()) {

            return new ModelAndView("form");
        }

        Person newPerson = clicService.createPerson(registrationForm);

        clicService.registerClic(resolvedLink.get(), newPerson);

        Cookie cookie = new Cookie(VISITOR_COOKIE, newPerson.getVisitorId().toString());

        cookie.setMaxAge(360 * 24 * 60 * 60);

        cookie.setSecure(true);

        cookie.setHttpOnly(true);

        cookie.setPath("/");

        response.addCookie(cookie);

        return new ModelAndView("redirect:" + resolvedLink.get().getUrl());

    }


    @GetMapping("/r/{shortCode}/anonymous")
    public ModelAndView registerAnonymousClic(@PathVariable String shortCode ) {

        Optional<Link> resolvedLink = clicService.resolveLink(shortCode);

        if (resolvedLink.isEmpty()) {

            return errorView();
        }

        clicService.registerAnonymousClic(resolvedLink.get());

        return new ModelAndView("redirect:" + resolvedLink.get().getUrl());


    }

    private ModelAndView errorView() {

        ModelAndView modelAndView = new ModelAndView("error/404");

        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;

    }
}


