package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Autowired
    FotografRepository fotografRepository;

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(){
        // Returnerer viewet "login.html" med map.addisjonen
        return "login";
    }
    @RequestMapping(path="/nybruker", method = RequestMethod.GET)
    public String nybruker(){
        // Returnerer viewet "login.html" med map.addisjonen
        return "nybruker";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public @ResponseBody String autoriser(){
        // Returnerer viewet "rediger.html"
        return "rediger";
    }

    @RequestMapping(path="/nybruker", method = RequestMethod.POST)
    public String lagBruker(@RequestParam(value="fornavn") String fornavn, @RequestParam(value="etternavn") String etternavn,
                            @RequestParam(value="brukernavn") String bruker, @RequestParam(value="passord") String passord){
        Fotograf fotograf = new Fotograf(fornavn, etternavn, bruker, passord);
        fotografRepository.save(fotograf);
        return "Lagret";
    }

}
