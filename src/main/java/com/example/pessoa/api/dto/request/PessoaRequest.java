package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.Email;
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
public class PessoaRequest {

    @NotBlank
    @Length(max = 100)
    private String name;

    @NotNull
    @Positive
    private Integer age;

    @Email
    @NotBlank
    @Length(max = 50)
    private String email;

    @NotNull(message = "O campo idEndereco é obrigatório.")
    private Long idEndereco;
}
