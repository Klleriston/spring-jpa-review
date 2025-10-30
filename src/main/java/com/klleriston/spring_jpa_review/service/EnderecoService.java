package com.klleriston.spring_jpa_review.service;

import com.klleriston.spring_jpa_review.entity.Endereco;
import com.klleriston.spring_jpa_review.repository.EnderecoRepository;
import com.klleriston.spring_jpa_review.specification.EnderecoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco save(Endereco endereco) {
        return this.enderecoRepository.save(endereco);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndCidade(String uf, String cidade) {
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecification.likeUf(uf).and(EnderecoSpecification.likeCidade(cidade))
        );
        return this.enderecoRepository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndLougradouro(String uf, String logradouro) {
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecification.likeUf(uf).and(EnderecoSpecification.likeLogradouro(logradouro))
        );
        return this.enderecoRepository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorNomeOrSobrenome(String nome, String sobrenome) {
        Specification<Endereco> spec = Specification.where(
                EnderecoSpecification.likeAutorNome(nome).or(EnderecoSpecification.likeAutorSobreNome(sobrenome))
        );
        return this.enderecoRepository.findAll(spec);
    }

    @Transactional(readOnly = true)
    public Endereco findByAutorNomeAndSobrenome(String nome, String sobrenome) {
        return this.enderecoRepository.findOne(EnderecoSpecification.likeAutorNomeAndSobreNome(nome, sobrenome)).orElseGet(Endereco::new);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorTotalDePostsAndCidade(long total, List<String> cidades) {
        Specification<Endereco> spec = Specification
                .where(EnderecoSpecification.
                        inCidades(cidades)
                        .and(EnderecoSpecification
                                .byGreaterThanEqualtOPosts(total)));

        return this.enderecoRepository.findAll(spec);
    }


}
