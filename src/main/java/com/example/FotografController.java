package com.example;

import com.example.Storage.StorageFileNotFoundException;
import com.example.Storage.StorageService;
import com.mongodb.util.JSON;
import com.sun.deploy.security.UserDeclinedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class FotografController {

    private final StorageService storageService;

    @Autowired
    public FotografController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    FotografRepository fotografRepository;
    @Autowired
    FotoRepository fotoRepository;

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public @ResponseBody String slett(@RequestParam("id") String id ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fotograf fgraf = fotografRepository.findByBrukernavn(user.getUsername());
        String fid = fgraf.getId();
        Foto foto =  fotoRepository.findById(id);
        if( !fgraf.getId().equals(foto.getFotografId())) {
            return "Noe ble feil";
        }
        else {
            fotoRepository.removeById(id);
            if (storageService.loadAsResource(id+".jpg").exists()){
                storageService.slett(id+".jpg");
                return "slettet";
            }
            else{
                return "Ingen fil med dette navn";
            }
        }
    }

    @RequestMapping(path = "/fotograf", method = RequestMethod.GET)
    public ModelAndView fotograf(ModelAndView mav){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fotograf fgraf = fotografRepository.findByBrukernavn(user.getUsername());
        String id = fgraf.getId();
        List<Foto> liste = fotoRepository.findAllByFotografId(id);
        mav.setViewName("fotograf");
        mav.addObject("liste", liste);
        return mav;
    }

    @RequestMapping(path="/nybruker", method = RequestMethod.GET)
    public String nybruker(){
        // Returnerer viewet "login.html" med map.addisjonen
        return "nybruker";
    }

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(){
        // Returnerer viewet "login.html" med map.addisjonen
        return "login";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public @ResponseBody String autoriser(){
        // Returnerer viewet "rediger.html"
        return "rediger";
    }

    @RequestMapping(path="/nyttbilde", method = RequestMethod.GET)
    public String nyttBilde(){
        // Returnerer viewet "rediger.html"
        return "nyttbilde";
    }

    @RequestMapping(path = "/rediger", method = RequestMethod.GET)
    public String rediger(){
        // Returnerer viewet "rediger.html"
        return "rediger";
    }

    @RequestMapping(path="/nybruker", method = RequestMethod.POST)
    public ModelMap lagBruker(@RequestParam(value="fornavn") String fornavn, @RequestParam(value="etternavn") String etternavn,
                                     @RequestParam(value="brukernavn") String bruker, @RequestParam(value="passord") String passord){
        Fotograf user = fotografRepository.findByBrukernavn(bruker);
        ModelMap map = new ModelMap();
        if(user != null) {
            map.addAttribute( "error", "nybruker");
            return map;
        }
        else {
            map.addAttribute("lagret", "nybruker");
                        Fotograf fotograf = new Fotograf(fornavn, etternavn, bruker, passord);
            fotografRepository.save(fotograf);
            return map;
        }
    }
}
