package com.example.pessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pessoa.domain.Pessoa;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    boolean existsByEmail(String email);
}
