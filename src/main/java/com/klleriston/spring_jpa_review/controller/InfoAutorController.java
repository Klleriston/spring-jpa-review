package com.klleriston.spring_jpa_review.controller;

import com.klleriston.spring_jpa_review.entity.InfoAutor;
import com.klleriston.spring_jpa_review.service.InfoAutorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("info")
public class InfoAutorController {

    private final InfoAutorService infoAutorService;

    public InfoAutorController(InfoAutorService infoAutorService) {
        this.infoAutorService = infoAutorService;
    }

    @GetMapping("{id}")
    public InfoAutor getInfoAutor(@PathVariable Long id){
        return infoAutorService.findById(id);
    }

    @GetMapping("cargo/{cargo}")
    public List<InfoAutor> getInfoAutor(@PathVariable String cargo){
        return infoAutorService.findAllContainsCargo(cargo);
    }

    @GetMapping("cargo/{cargo}/empresa/{empresa}")
    public List<InfoAutor> getInfoAutor(@PathVariable String cargo, @PathVariable String empresa){
        return infoAutorService.findAllCargoAndEmpresa(cargo, empresa);
    }

    @GetMapping("bio/{bio}")
    public InfoAutor getFromBio(@PathVariable String bio){
        return infoAutorService.findFromBio(bio);
    }
}
