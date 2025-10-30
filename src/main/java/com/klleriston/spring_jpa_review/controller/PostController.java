package com.klleriston.spring_jpa_review.controller;

import com.klleriston.spring_jpa_review.entity.Post;
import com.klleriston.spring_jpa_review.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostExchange
    public Post salvar(Post post) {
        return this.postService.save(post);
    }

    @GetMapping("categoria/{categoria}/autor/{autorId}")
    public List<Post> getByCategoriaAndAutor(@PathVariable String categoria, @PathVariable Long autorId) {
        return this.postService.findAllByCategoriaAndAutorId(categoria, autorId);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public List<Post> getByAutor(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.postService.findAllByAutor(nome, sobrenome);
    }

    @GetMapping("titulo/{titulo}")
    public List<Post> getByTitulo(@PathVariable String titulo) {
        return this.postService.findAllByTitulo(titulo);
    }

    @GetMapping("data-publicacao/{data}")
    public List<Post> getByDataPublicacao(@PathVariable LocalDate data) {
        return this.postService.findAllDataPublicacaoMaiorOuIgual(data);
    }

    @GetMapping("sem-data-publicacao")
    public List<Post> getBySemDataPublicacao() {
        return this.postService.findAllBySemDataPublicacao();
    }

    @GetMapping("all")
    public Page<Post> getAllPagination(@PageableDefault(page = 0, size = 10, sort = "dataPublicacao", direction = Sort.Direction.DESC) Pageable pageable) {
        return this.postService.pageAllPagination(pageable);
    }

    @GetMapping("ano/{ano}")
    public Page<Post> getAllPagination(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "dataPublicacao") String sort,
                                       @RequestParam(defaultValue = "DESC") String dir,
                                       @PathVariable int ano) {
        return this.postService.pageAllByAno(ano, page, size, sort, dir);
    }
}
