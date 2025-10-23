package com.klleriston.spring_jpa_review.service;

import com.klleriston.spring_jpa_review.entity.InfoAutor;
import com.klleriston.spring_jpa_review.repository.InfoAutorRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InfoAutorService {
    private final InfoAutorRepository infoAutorRepository;

    public InfoAutorService(InfoAutorRepository infoAutorRepository) {
        this.infoAutorRepository = infoAutorRepository;
    }

    public InfoAutor findById(Long id) {
        InfoAutor info = new InfoAutor();
        info.setId(id);
        return this.infoAutorRepository.findOne(Example.of(info)).orElseGet(InfoAutor::new);
    }

    public List<InfoAutor> findAllContainsCargo(String cargo) {
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.contains());
        return this.infoAutorRepository.findAll(Example.of(info,  matcher));
    }

    public List<InfoAutor> findAllCargoAndEmpresa(String cargo, String empresa) {
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);
        info.setBio(empresa);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains());
        return this.infoAutorRepository.findAll(Example.of(info,  matcher));
    }

    public InfoAutor findFromBio(String bio){
        InfoAutor info = new InfoAutor();
        info.setBio(bio);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains());
        return this.infoAutorRepository.findBy(
                Example.of(info,  matcher),
                query -> query.sortBy(Sort.by("cargo")
                        .descending())
                        .first()
                        .orElseGet(InfoAutor::new));
    }


}
