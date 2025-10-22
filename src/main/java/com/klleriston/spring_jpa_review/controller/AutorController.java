package com.klleriston.spring_jpa_review.controller;

import com.klleriston.spring_jpa_review.entity.InfoAutor;
import com.klleriston.spring_jpa_review.projection.AutorInfoProjection;
import com.klleriston.spring_jpa_review.service.AutorService;
import com.klleriston.spring_jpa_review.entity.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        autorService.save(autor);
        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor) {
        autorService.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id) {
        autorService.delete(id);
        return "Autor id = " + id + " foi excluído com sucesso";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id) {
        return autorService.findById(id);
    }

    @GetMapping
    public List<Autor> getAll() {
        return autorService.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAutoresByNomeOrSobrenome(@RequestParam String termo) {
        return autorService.findAllByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalDeAutores() {
        return autorService.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        return autorService.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getByCargo(@RequestParam String cargo) {
        return autorService.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection salvarInfoAutor(@RequestParam Long id) {
        return autorService.findAutorInfoById(id);
    }


}

