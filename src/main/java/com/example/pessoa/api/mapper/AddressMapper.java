package com.example.pessoa.api.mapper;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.entity.Address;

public class AddressMapper {

    public static Address toAddressEntity(AddressRequest request){

        Address address = new Address();
        address.setStreetName(request.getStreetName());
        address.setHouseNumber(request.getHouseNumber());
        address.setDistrict(request.getDistrict());
        address.setCity(request.getCity());
        address.setState(request.getState());
        return address;
    }

    public static AddressResponse toAddressResponse(Address address){
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setNomeDaRua(address.getStreetName());
        response.setNumeroDaCasa(address.getHouseNumber());
        response.setBairro(address.getDistrict());
        response.setCidade(address.getCity());
        response.setEstado(address.getState());
        return response;
    }

    public static void atualizaAddress(Address entity, AddressRequest request) {
        entity.setStreetName(request.getStreetName());
        entity.setHouseNumber(request.getHouseNumber());
        entity.setDistrict(request.getDistrict());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
    }
}
