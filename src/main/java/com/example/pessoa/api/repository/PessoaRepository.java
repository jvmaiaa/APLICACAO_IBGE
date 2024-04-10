package com.example.pessoa.api.repository;

import com.example.pessoa.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Person, Long>{

    boolean existsByEmail(String email);

    Optional<Person> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
