package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(){
        // Returnerer viewet "login.html" med map.addisjonen
        return "login";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public @ResponseBody
    String autoriser(){
        // Returnerer viewet "rediger.html"
        return "rediger";
    }

}
