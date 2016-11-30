package com.example;

import com.example.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
    @RequestMapping(path = "/kommentar/slett", method = RequestMethod.POST)
    public @ResponseBody String slettTil(@RequestParam(value = "id")String id, @RequestParam(value = "bilde")String bildeid){
        Foto foto = fotoRepository.findById(bildeid);
        System.out.println(bildeid+" "+id);
        foto.slettKommentar(id);
        fotoRepository.save(foto);
        return "slettet";
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

    @RequestMapping(path="/nyttbilde", method = RequestMethod.GET)
    public String nyttBilde(){
        // Returnerer viewet "rediger.html"
        return "nyttbilde";
    }

    @RequestMapping(path = "/rediger", method = RequestMethod.GET)
    public ModelAndView finn(@RequestParam(value = "id") String id, ModelAndView mav){
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Fotograf fgraf = fotografRepository.findByBrukernavn(user.getUsername());
        Foto foto =  fotoRepository.findById(id);
        /*if( !fgraf.getId().equals(foto.getFotografId())) {
            return new ModelAndView("rediger");
        }
        else{*/
            mav.setViewName("rediger");
            mav.addObject("bilde", foto);
            return mav;
        //}
    }

    @RequestMapping(path = "/rediger", method = RequestMethod.POST)
    public @ResponseBody String lagreEndring(@RequestParam(value = "id") String id, @RequestParam(value = "tittel") String tittel, @RequestParam(value = "tags")List tags){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fotograf fgraf = fotografRepository.findByBrukernavn(user.getUsername());
        String ttl = tittel;
        System.out.println(ttl);
        Foto foto =  fotoRepository.findById(id);
        if( !fgraf.getId().equals(foto.getFotografId())) {
            return "error";
        }
        else{
            foto.setTittel(tittel);
            foto.setTags(tags);
            fotoRepository.save(foto);
            return "lagret";
        }
    }
}
