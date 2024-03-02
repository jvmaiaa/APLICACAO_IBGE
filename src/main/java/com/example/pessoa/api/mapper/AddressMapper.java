package com.example.pessoa.api.mapper;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.entity.Address;

public class AddressMapper {

    public static Address toAddressEntity(AddressRequest request){

        Address address = new Address();
        address.setNomeDaRua(request.getNomeDaRua());
        address.setNumeroDaCasa(request.getNumeroDaCasa());
        address.setBairro(request.getBairro());
        address.setCidade(request.getCidade());
        address.setEstado(request.getEstado());
        return address;
    }

    public static AddressResponse toAddressResponse(Address address){
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setNomeDaRua(address.getNomeDaRua());
        response.setNumeroDaCasa(address.getNumeroDaCasa());
        response.setBairro(address.getBairro());
        response.setCidade(address.getCidade());
        response.setEstado(address.getEstado());
        return response;
    }

    public static void atualizaAddress(Address entity, AddressRequest request) {
        entity.setNomeDaRua(request.getNomeDaRua());
        entity.setNumeroDaCasa(request.getNumeroDaCasa());
        entity.setBairro(request.getBairro());
        entity.setCidade(request.getCidade());
        entity.setEstado(request.getEstado());
    }
}
