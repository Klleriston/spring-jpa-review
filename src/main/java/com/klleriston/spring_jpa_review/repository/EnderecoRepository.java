package com.klleriston.spring_jpa_review.repository;

import com.klleriston.spring_jpa_review.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>, JpaSpecificationExecutor<Endereco> {

    @Modifying
    @Query("UPDATE Endereco e set e.bariro = :bairro, e.logradouro = :logradouro, e.numero = :numero" +
    " WHERE e.id = :id")
    int updateByBairroAndLogradouroAndNumero(Long id, String bairro, String logradouro, int numero);

    @Query(nativeQuery = true, value = "SELECT * FROM Enderecos WHERE uf LIKE :uf")
    List<Endereco> findEnderecoByUf(String uf);
}
