package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    FotografRepository fotografRepository;
    @Autowired
    FotoRepository fotoRepository;

    @RequestMapping(path = "/sok", method = RequestMethod.GET)
    public @ResponseBody
    List<Foto> finn(@RequestParam(value = "sokeord") String sokeord){
        System.out.println(sokeord);
        return fotoRepository.findAllByTittelStartsWithIgnoreCase(sokeord);
    }

    @RequestMapping(path = "/bilde", method = RequestMethod.GET)
    public ModelAndView finn(@RequestParam(value = "bilde") String bilde, ModelAndView mav){
        Foto foto =  fotoRepository.findById(bilde);
        mav.setViewName("bilde");
        mav.addObject("bilde", foto);
        return mav;
    }
    @RequestMapping(path = "/kommentar", method = RequestMethod.POST)
    public @ResponseBody Kommentar leggTil(@RequestParam(value = "id")String id, @RequestParam(value = "navn")String navn,
                                           @RequestParam(value = "kommentar") String kommentar){
        Foto bilde = fotoRepository.findById(id);
        Kommentar kommentaren = new Kommentar();
        kommentaren.setNavn(navn);
        kommentaren.setKommentar(kommentar);
        Date tid = new Date();
        kommentaren.setDato(tid);
        bilde.addKommentarer(kommentaren);
        fotoRepository.save(bilde);
        return kommentaren;
    }

}
