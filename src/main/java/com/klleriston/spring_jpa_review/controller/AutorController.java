package com.klleriston.spring_jpa_review.controller;

import com.klleriston.spring_jpa_review.dao.AutorDao;
import com.klleriston.spring_jpa_review.entity.Autor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {
    private final AutorDao autorDao;

    public AutorController(AutorDao autorDao) {
        this.autorDao = autorDao;
    }

    @PostMapping
    public Autor Salvar(@RequestBody Autor autor) {
        autorDao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor Atualizar(@RequestBody Autor autor) {
        autorDao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String Remover(@PathVariable Long id) {
        autorDao.delete(id);
        return "autor " + id + " removido do sistema !";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id) {
        return autorDao.findById(id);
    }

    @GetMapping()
    public List<Autor> getAll() {
        return autorDao.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getByNameOrSobrenome(@RequestParam String termo) {
        return autorDao.FindByNameOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long GetTotalAutores() {
        return autorDao.getTotalElements();
    }


}
