package com.example.pessoa.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long idAddress;
}
