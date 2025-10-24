package com.klleriston.spring_jpa_review.service;

import com.klleriston.spring_jpa_review.entity.Categoria;
import com.klleriston.spring_jpa_review.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = false)
    public List<Categoria> save(List<Categoria> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    @Transactional(readOnly = true)
    public Categoria findByTitulo(String titulo) {
        return categoriaRepository.findByTitulo(titulo).orElseGet(Categoria::new);
    }

    @Transactional(readOnly = true)
    public Categoria findByInicioTitulo(String titulo) {
        return categoriaRepository.findByTituloStartsWith(titulo).orElseGet(Categoria::new);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findByTitulos(List<String> titulos) {
        return categoriaRepository.findByTitulosIn(titulos);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAllOrderByTituloAsc() {
        return categoriaRepository.findAllOrderByTituloAsc();
    }

}
