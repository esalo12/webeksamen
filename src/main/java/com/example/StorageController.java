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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import java.lang.reflect.Array;
import java.util.stream.Collectors;


@Controller
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    FotoRepository fotoRepository;
    @Autowired
    FotografRepository fotografRepository;

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(StorageController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
        return "home";

    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }


    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, @RequestParam("tittel") String tittel,
                                   RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Foto foto = new Foto();
        Fotograf fotgraf = fotografRepository.findByBrukernavn(user.getUsername());
        foto.setFotografId(fotgraf.getId());
        foto.setContentType(file.getContentType());
        foto.setTittel(tittel);
        foto.setDato();
        foto.setKommentarer();
        System.out.println(foto.getTittel()+", "+foto.getDato()+" "+file.getSize());
        fotoRepository.save(foto);
        foto.setFilnavn(foto.getId()+"."+file.getOriginalFilename().split("\\.")[1]);
        fotoRepository.save(foto);
        storageService.store(file, foto.getFilnavn());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/fotograf";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}

