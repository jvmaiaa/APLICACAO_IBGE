package com.example.pessoa.api.repository;

import com.example.pessoa.api.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    boolean existsByEmail(String email);

    Optional<Pessoa> findByEmail(String email);
}
