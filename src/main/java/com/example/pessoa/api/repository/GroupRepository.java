package com.example.pessoa.api.repository;

import com.example.pessoa.api.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {

    Optional<Group> findByNome(String nome);
}
