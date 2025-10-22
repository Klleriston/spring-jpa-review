package com.klleriston.spring_jpa_review.repository;

import com.klleriston.spring_jpa_review.entity.Autor;
import com.klleriston.spring_jpa_review.projection.AutorInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.infoAutor.cargo like :cargo ORDER BY a.nome asc")
    List<Autor> findByCargo(@Param("cargo") String cargo);

    @Query("SELECT a FROM Autor a WHERE a.nome like :termo OR a.sobrenome LIKE :termo")
    List<Autor> findAllByNomeOrSobrenome(String termo);

    @Query("SELECT a.nome AS nome, a.sobrenome AS sobrenome, a.infoAutor.cargo AS cargo, a.infoAutor.bio AS bio FROM Autor a WHERE a.id = :id")
    AutorInfoProjection findAutorInfoById(Long id);
}
