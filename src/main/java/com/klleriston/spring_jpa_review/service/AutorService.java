package com.klleriston.spring_jpa_review.service;

import com.klleriston.spring_jpa_review.entity.Autor;
import com.klleriston.spring_jpa_review.entity.InfoAutor;
import com.klleriston.spring_jpa_review.projection.AutorInfoProjection;
import com.klleriston.spring_jpa_review.repository.AutorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void save(Autor autor) {
        this.autorRepository.save(autor);
    }

    @Transactional(readOnly = false)
    public void update(Autor autor) {
        this.autorRepository.save(autor);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.autorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        return this.autorRepository.findById(id).orElseGet(Autor::new);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
       return this.autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllByNomeOrSobrenome(String termo) {
        return this.autorRepository.findAllByNomeOrSobrenome(termo);
    }

    @Transactional(readOnly = true)
    public Long getTotalElements() {
      return this.autorRepository.count();
    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(InfoAutor infoAutor, Long autorId) {
        Autor autor = findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo) {
        return this.autorRepository.findByCargo("%" + cargo + "%");
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id) {
        String query = """
                select new AutorInfoProjection(a.nome, a.sobrenome, a.infoAutor.cargo, a.infoAutor.bio)
                from Autor a
                where a.id = :id
                """;
        return autorRepository.findAutorInfoById(id);
    }

}
