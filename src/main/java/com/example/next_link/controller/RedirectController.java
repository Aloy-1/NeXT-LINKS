package com.example.next_link.controller;

import com.example.next_link.dto.RegistrationForm;
import com.example.next_link.dto.ResolvedLink;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class RedirectController {

    private final ClicService clicService;
    static final String VISITOR_COOKIE = "visitorId";

    @GetMapping("/r")

    public ModelAndView redirect (@RequestParam("videoId") Integer requestVideo , @RequestParam("canal") String requestCanal, @CookieValue(name = VISITOR_COOKIE, required = false ) String visitorId) {

        Optional<ResolvedLink> resolvedLink = clicService.resolveLink(requestCanal, requestVideo);

        if (resolvedLink.isPresent()){

            Optional<Person> person = clicService.resolvePerson(visitorId);

            if(person.isPresent()){

                clicService.registerClic(resolvedLink.get().canal(), resolvedLink.get().video(), person.get());

                return new ModelAndView("redirect:" + resolvedLink.get().video().getLink() );
            }
            else {

                return new ModelAndView("form")
                        .addObject("formInfosUser",
                                new RegistrationForm( resolvedLink.get().canal().getName(),
                                        resolvedLink.get().video().getId(), null, null , null , null));
            }

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Lien de suivi introuvable");
        }
    }


    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("formInfosUser")  @Validated  RegistrationForm registrationForm , BindingResult bindingResult , HttpServletResponse response){

        if(bindingResult.hasErrors()){

            return new ModelAndView("form");
        }

        Optional<ResolvedLink> resolvedLink = clicService.resolveLink(registrationForm.canal(), registrationForm.videoId());

        if (resolvedLink.isPresent()){

            Person person = clicService.findOrCreatePerson(registrationForm);

            clicService.registerClic(resolvedLink.get().canal(), resolvedLink.get().video(), person);

            Cookie cookie = new Cookie(VISITOR_COOKIE , person.getVisitorId().toString());

            cookie.setMaxAge(360 * 24 * 60 * 60);
            //cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            return new ModelAndView("redirect:" + resolvedLink.get().video().getLink() );
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Lien de suivi introuvable");
        }

    }
}
