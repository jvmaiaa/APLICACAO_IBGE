package com.example.pessoa.api.repository;

import com.example.pessoa.api.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    boolean existsByEmail(String email);
}
