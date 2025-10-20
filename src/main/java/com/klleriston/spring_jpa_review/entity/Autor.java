package com.klleriston.spring_jpa_review.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_AUTORES")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor", nullable = false)
    private long id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 45)
    private String sobrenome;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @JoinColumn(name = "id_info")
    private InfoAutor infoAutor;

    public InfoAutor getInfoAutor() {
        return infoAutor;
    }

    public void setInfoAutor(InfoAutor infoAutor) {
        this.infoAutor = infoAutor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id && Objects.equals(nome, autor.nome) && Objects.equals(sobrenome, autor.sobrenome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                '}';
    }
}
