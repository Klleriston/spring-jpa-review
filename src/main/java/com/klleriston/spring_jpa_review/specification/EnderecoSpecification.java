package com.klleriston.spring_jpa_review.specification;

import com.klleriston.spring_jpa_review.entity.Autor;
import com.klleriston.spring_jpa_review.entity.Endereco;
import com.klleriston.spring_jpa_review.entity.Post;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class EnderecoSpecification {

    public static Specification<Endereco> likeUf(String uf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("uf"), uf));
    }

    public static Specification<Endereco> likeCidade(String cidade) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("cidade"), cidade));
    }

    public static Specification<Endereco> likeLogradouro(String logradouro) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("logradouro"), "%" + logradouro + "%"));
    }

    public static Specification<Endereco> inCidades(List<String> cidades) {
        return ((root, query, criteriaBuilder) ->
                root.get("cidade").in(cidades));
    }

    public static Specification<Endereco> likeAutorNome(String nomeAutor) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("autor").get("nome"), nomeAutor));
    }

    public static Specification<Endereco> likeAutorSobreNome(String sobrenomeAutor) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("autor").get("sobrenome"), sobrenomeAutor));
    }

    public static Specification<Endereco> likeAutorNomeAndSobreNome(String nome, String sobrenomeAutor) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get("autor").get("nome"), nome),
                        criteriaBuilder.like(root.get("autor").get("sobrenome"), sobrenomeAutor)
                ));
    }

    public static Specification<Endereco> byGreaterThanEqualtOPosts(long total) {
        return ((root, query, criteriaBuilder) -> {
            Join<Endereco, Autor> autor = root.join("autor");
            Join<Autor, Post> post =  root.join("post");
            query.groupBy(post.get("autor"));
            query.having(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.count(post.get("id")),  total));

            return query.getRestriction();
        });
    }
}
