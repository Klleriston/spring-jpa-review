package com.klleriston.spring_jpa_review.controller;

import com.klleriston.spring_jpa_review.entity.Categoria;
import com.klleriston.spring_jpa_review.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping()
    public List<Categoria> getByTitulo(@RequestBody List<Categoria> categorias) {
        return this.categoriaService.save(categorias);
    }

    @GetMapping("titulo/{titulo}")
    public Categoria getByTitulo(@PathVariable String titulo){
        return this.categoriaService.findByTitulo(titulo);
    }

    @GetMapping("titulo/inicio/{titulo}")
    public Categoria getByInicioTipo(@PathVariable String titulo){
        return this.categoriaService.findByInicioTitulo(titulo);
    }

    @GetMapping("titulos")
    public List<Categoria> getByTitulos(@RequestParam(name = "t") List<String> titulos){
        return this.categoriaService.findByTitulos(titulos);
    }

    @GetMapping()
    public List<Categoria> getCategoriaOrdened(){
        return this.categoriaService.findAllOrderByTituloAsc();
    }

}
