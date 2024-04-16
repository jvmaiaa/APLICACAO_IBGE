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
        response.setStreetName(address.getStreetName());
        response.setHouseNumber(address.getHouseNumber());
        response.setDistrict(address.getDistrict());
        response.setCity(address.getCity());
        response.setState(address.getState());
        return response;
    }

    public static void updateAddress(Address entity, AddressRequest request) {
        entity.setStreetName(request.getStreetName() != null ? request.getStreetName() : entity.getStreetName());
        entity.setHouseNumber(request.getHouseNumber() != null ? request.getHouseNumber() : entity.getHouseNumber());
        entity.setDistrict(request.getDistrict() != null ? request.getDistrict() : entity.getDistrict());
        entity.setCity(request.getCity() != null ? request.getCity() : entity.getCity());
        entity.setState(request.getState() != null ? request.getState() : entity.getState());
    }
}
