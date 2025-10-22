package com.klleriston.spring_jpa_review.projection;

import org.springframework.beans.factory.annotation.Value;

public interface AutorInfoProjection {
    @Value("#{target.nome + ' ' + target.sobreome}")
    String getNomeCompleto();
    String getCargo();
    String getBio();
}
