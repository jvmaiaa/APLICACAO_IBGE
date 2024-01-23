package com.example.pessoa.domain;

// Para permitir todas as importações de uma dependencia, é usado "*" no import
import com.example.pessoa.DTOs.RequestAddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_address")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDaRua;
    private Integer numeroDaCasa;
    private String bairro;
    private String cidade;
    private String estado;


    @JsonIgnore
    @OneToMany(mappedBy = "endereco")
    private List<Pessoa> pessoas = new ArrayList<>();

    public Address (RequestAddressDTO requestAddressDTO){
        this.nomeDaRua = requestAddressDTO.nomeDaRua();
        this.numeroDaCasa = requestAddressDTO.numeroDaCasa();
        this.bairro = requestAddressDTO.bairro();
        this.cidade = requestAddressDTO.cidade();
        this.estado = requestAddressDTO.estado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDaRua() {
        return nomeDaRua;
    }

    public void setNomeDaRua(String nomeDaRua) {
        this.nomeDaRua = nomeDaRua;
    }

    public Integer getNumeroDaCasa() {
        return numeroDaCasa;
    }

    public void setNumeroDaCasa(Integer numeroDaCasa) {
        this.numeroDaCasa = numeroDaCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
