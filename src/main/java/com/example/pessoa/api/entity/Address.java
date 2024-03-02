package com.example.pessoa.api.entity;

// Para permitir todas as importações de uma dependencia, é usado "*" no import

import com.example.pessoa.api.dto.request.AddressRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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

    @NotNull
    @NotBlank
    @Length(max = 100)
    @Column(length = 100, nullable = false)
    private String nomeDaRua;

    @NotNull
    @Positive
    @Column(length = 4, nullable = false)
    private Integer numeroDaCasa;

    @NotNull
    @NotBlank
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String bairro;

    @NotNull
    @NotBlank
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String cidade;

    @NotNull
    @NotBlank
    @Length(max = 10)
    @Column(length = 10, nullable = false)
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
