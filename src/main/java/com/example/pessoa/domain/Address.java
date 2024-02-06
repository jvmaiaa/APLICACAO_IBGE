package com.example.pessoa.domain;

// Para permitir todas as importações de uma dependencia, é usado "*" no import

import com.example.pessoa.DTOs.RequestAddressDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_address")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nomeDaRua;

    @Getter
    @Setter
    private Integer numeroDaCasa;

    @Getter
    @Setter
    private String bairro;

    @Getter
    @Setter
    private String cidade;

    @Getter
    @Setter
    private String estado;


    @Getter
    @OneToMany(mappedBy = "endereco", cascade = CascadeType.PERSIST)
    private List<Pessoa> pessoas = new ArrayList<>();

    public Address (RequestAddressDTO requestAddressDTO){
        this.nomeDaRua = requestAddressDTO.nomeDaRua();
        this.numeroDaCasa = requestAddressDTO.numeroDaCasa();
        this.bairro = requestAddressDTO.bairro();
        this.cidade = requestAddressDTO.cidade();
        this.estado = requestAddressDTO.estado();
    }

}
