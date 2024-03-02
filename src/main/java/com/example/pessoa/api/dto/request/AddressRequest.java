package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotNull
    @NotBlank
    @Length(max = 100)
    private String nomeDaRua;

    @NotNull
    @Positive
    private Integer numeroDaCasa;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String bairro;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String cidade;

    @NotNull
    @NotBlank
    @Length(max = 10)
    private String estado;
}
