package com.example.pessoa.api.entity;

// Para permitir todas as importações de uma dependencia, é usado "*" no import

import com.example.pessoa.api.dto.request.AddressRequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_address")
@EqualsAndHashCode(of = "id")
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


    @OneToMany(mappedBy = "endereco", cascade = CascadeType.PERSIST)
    private List<Pessoa> pessoas = new ArrayList<>();

    public Address (AddressRequest requestAddressDTO){
        this.nomeDaRua = requestAddressDTO.getNomeDaRua();
        this.numeroDaCasa = requestAddressDTO.getNumeroDaCasa();
        this.bairro = requestAddressDTO.getBairro();
        this.cidade = requestAddressDTO.getCidade();
        this.estado = requestAddressDTO.getEstado();
    }

}
