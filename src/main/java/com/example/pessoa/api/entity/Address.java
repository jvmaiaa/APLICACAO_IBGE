package com.example.pessoa.api.entity;

// Para permitir todas as importações de uma dependencia, é usado "*" no import

import com.example.pessoa.api.dto.request.AddressRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_address")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String streetName;

    private Integer houseNumber;

    private String district;

    private String city;

    private String state;

    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST)
    private List<Person> people = new ArrayList<>();

    public Address(AddressRequest requestAddressDTO){
        this.streetName = requestAddressDTO.getStreetName();
        this.houseNumber = requestAddressDTO.getHouseNumber();
        this.district = requestAddressDTO.getDistrict();
        this.city = requestAddressDTO.getCity();
        this.state = requestAddressDTO.getState();
    }

}
