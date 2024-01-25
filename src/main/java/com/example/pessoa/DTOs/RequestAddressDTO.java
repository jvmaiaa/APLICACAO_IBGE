package com.example.pessoa.DTOs;

import com.example.pessoa.domain.Address;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

public record RequestAddressDTO(
        @NonNull String nomeDaRua,
        @NonNull Integer numeroDaCasa,
        @NonNull String bairro,
        @NonNull String cidade,
        @NonNull String estado,
        @NonNull Long idPessoa) {
}
