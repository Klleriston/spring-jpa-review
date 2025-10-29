package com.klleriston.spring_jpa_review.service;

import com.klleriston.spring_jpa_review.entity.Autor;
import com.klleriston.spring_jpa_review.entity.Categoria;
import com.klleriston.spring_jpa_review.entity.Post;
import com.klleriston.spring_jpa_review.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private AutorService autorService;
    private CategoriaService categoriaService;

    public PostService(PostRepository postRepository,  AutorService autorService, CategoriaService categoriaService) {
        this.postRepository = postRepository;
        this.autorService = autorService;
        this.categoriaService = categoriaService;
    }

    @Transactional
    public Post save(Post post) {
        Autor autor = autorService.findById(post.getAutor().getId());
        post.setAutor(autor);

        List<String> titulos = post.getCategoria().stream()
                .map(Categoria::getTitulo)
                .toList();

        List<Categoria> categorias = categoriaService.findByTitulos(titulos);
        post.setCategoria(categorias);

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategoriaAndAutorId(String categoria, Long autorId) {
        return this.postRepository.findByCategoriaTituloAndAutorId(categoria, autorId);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByAutor(String autorNome, String autorSobrenome) {
       return this.postRepository.findByAutorNomeOrAutorSobrenome(autorNome, autorSobrenome);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByTitulo(String titulo) {
        return this.postRepository.findByTituloContainsOrderByAutorNomeAsc(titulo);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllDataPublicacaoMaiorOuIgual(LocalDate data) {
        return this.postRepository.findByDataPublicacaoIsGreaterThanEqual(data);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllBySemDataPublicacao() {
        return this.postRepository.findByDataPubicacaoIsNull();
    }


}
