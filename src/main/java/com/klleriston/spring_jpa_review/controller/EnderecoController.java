package com.klleriston.spring_jpa_review.controller;

import com.klleriston.spring_jpa_review.entity.Endereco;
import com.klleriston.spring_jpa_review.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class EnderecoController {
    private EnderecoService enderecoService;

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco) {
        return this.enderecoService.save(endereco);
    }

    @GetMapping("uf/{uf}/cidade/{cidade}")
    public List<Endereco> findByUfAndCidade(@PathVariable String uf, @PathVariable String cidade) {
        return this.enderecoService.findByUfAndCidade(uf, cidade);
    }

    @GetMapping("uf/{uf}/logradouro/{logradouro}")
    public List<Endereco> findByUfAndLogradouro(@PathVariable String uf, @PathVariable String logradouro) {
        return this.enderecoService.findByUfAndLougradouro(uf, logradouro);
    }

    @GetMapping("autores/nome/{nome}/sobrenome/{sobrenome}")
    public List<Endereco> getByAutoresNomesOrSobrenomes(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.enderecoService.findByAutorNomeOrSobrenome(nome, sobrenome);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public Endereco getByAutoreNomesAndSobrenomes(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.enderecoService.findByAutorNomeAndSobrenome(nome, sobrenome);
    }

    @GetMapping("autores/total-posts")
    public List<Endereco> getByAutoresTotal(@RequestParam long total, @RequestParam List<String> cidades) {
        return this.enderecoService.findByAutorTotalDePostsAndCidade(total, cidades);
    }
}
