package com.klleriston.spring_jpa_review.repository;

import com.klleriston.spring_jpa_review.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTitulo(String titulo);

    Optional<Categoria> findByTituloStartsWith(String titulo);

    List<Categoria> findByTitulosIn(List<String> titulos);

    List<Categoria> findAllOrderByTituloAsc();
}
