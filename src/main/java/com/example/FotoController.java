package com.example;


import com.example.Storage.StorageFileNotFoundException;
import com.example.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Controller
public class FotoController{

    private final StorageService storageService;

    @Autowired
    public FotoController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    FotoRepository fotoRepository;
    @Autowired
    FotografRepository fotografRepository;

    @RequestMapping(path = "/sok", method = RequestMethod.GET)
    public @ResponseBody List<Foto> finn(@RequestParam(value = "sokeord") String sokeord){
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

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FotoController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
        return "home";

    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename+".jpg");

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, @RequestParam("tittel") String tittel,
                                   RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Foto f = new Foto();
        Fotograf fotgraf = fotografRepository.findByBrukernavn(user.getUsername());
        f.setFotografId(fotgraf.getId());
        f.setTittel(file.getOriginalFilename());
        f.setContentType(file.getContentType());
        f.setTittel(tittel);
        f.setDato();
        f.setKommentarer();
        System.out.println(f.getTittel()+", "+f.getDato()+file.getSize());
        fotoRepository.save(f);
        System.out.println(f.getId());
        storageService.store(file, f.getId()+".jpg");
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/rediger";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

