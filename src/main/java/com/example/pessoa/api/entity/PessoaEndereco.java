package com.example.pessoa.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pessoaEndereco")
public class PessoaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Address endereco;

}
