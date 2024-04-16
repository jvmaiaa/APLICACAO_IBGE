package com.example.pessoa.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;
    private String streetName;
    private Integer houseNumber;
    private String district;
    private String city;
    private String state;

    private List<PersonResponse> people;
}
