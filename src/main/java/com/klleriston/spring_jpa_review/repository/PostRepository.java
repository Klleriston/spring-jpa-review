package com.klleriston.spring_jpa_review.repository;

import com.klleriston.spring_jpa_review.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoriaTituloAndAutorId(String categoria, Long autorId);

    List<Post> findByAutorNomeOrAutorSobrenome(String nome,  String sobrenome);

    List<Post> findByTituloContainsOrderByAutorNomeAsc(String titulo);

    List<Post> findByDataPublicacaoIsGreaterThanEqual(LocalDate data);

    List<Post> findByDataPubicacaoIsNull();

    Page<Post> pageAllPagination(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE YEAR(p.dataPublicacao = :ano")
    Page<Post> findByAno(int ano, Pageable pageable);
}
