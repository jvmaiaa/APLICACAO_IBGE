package com.example.pessoa.api.service.impl;

import com.example.pessoa.api.dto.request.AddressRequestDTO;
import com.example.pessoa.api.dto.response.AddressResponseDTO;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.exception.AddressNotFoundException;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.pessoa.api.mapper.AddressMapper.*;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<AddressResponseDTO> findAll(){
        return addressRepository.findAll()
                .stream()
                .map(address -> modelMapper.map(address, AddressResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AddressResponseDTO findById(Long id){
            Address currentAddress = addressRepository.findById(id).orElseThrow(
                    () -> new AddressNotFoundException(id));
            return modelMapper.map(currentAddress, AddressResponseDTO.class);
    }

    @Override
    public AddressResponseDTO insert(AddressRequestDTO obj) {
        Address address = toAddressEntity(obj);
        addressRepository.save(address);
        return modelMapper.map(address, AddressResponseDTO.class);
    }

    @Override
    public AddressResponseDTO update(Long id, AddressRequestDTO dto) {
            Address currentAddress = addressRepository
                    .findById(id)
                    .orElseThrow( () -> new AddressNotFoundException(id));

            updateAddress(currentAddress, dto);
            Address savedAddress = addressRepository.save(currentAddress);

            return toAddressResponse(savedAddress);
    }

    @Override
    public void delete(Long id) {
        addressRepository.delete(addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException(id)));
    }

}
