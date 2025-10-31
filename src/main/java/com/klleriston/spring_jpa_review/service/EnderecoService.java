package com.klleriston.spring_jpa_review.service;

import com.klleriston.spring_jpa_review.entity.Endereco;
import com.klleriston.spring_jpa_review.repository.EnderecoRepository;
import com.klleriston.spring_jpa_review.specification.EnderecoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.List;

@Service
public class EnderecoService {
    private final TransactionalOperator transactionalOperator;
    private EnderecoRepository enderecoRepository;

    public EnderecoService(TransactionalOperator transactionalOperator) {
        this.transactionalOperator = transactionalOperator;
    }

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

    @Transactional
    public int updateEndereco(long id, String bairro, String logradouro, int numero) {
        return this.enderecoRepository.updateByBairroAndLogradouroAndNumero(id, bairro, logradouro, numero);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUf(String uf) {
        return this.enderecoRepository.findEnderecoByUf(uf);
    }

}
