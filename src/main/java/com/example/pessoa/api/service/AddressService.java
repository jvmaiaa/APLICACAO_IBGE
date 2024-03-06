package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.exception.EnderecoNotFoundException;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.pessoa.api.mapper.AddressMapper.*;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<AddressResponse> findAll(){
        return addressRepository.findAll()
                .stream()
                .map(endereco -> modelMapper.map(endereco, AddressResponse.class)).collect(Collectors.toList());
    }

    public AddressResponse findById(Long id){
            Address enderecoEntity = addressRepository.findById(id).orElseThrow(() -> new EnderecoNotFoundException("Endereço não encontrado"));
            return modelMapper.map(enderecoEntity, AddressResponse.class);
    }

    public AddressResponse insert(AddressRequest obj) {
        Address endereco = toAddressEntity(obj);
        addressRepository.save(endereco);
        return modelMapper.map(endereco, AddressResponse.class);
    }

    public AddressResponse update(Long id, AddressRequest dto) {
            Address enderecoAtual = addressRepository
                    .findById(id)
                    .orElseThrow( () -> new EnderecoNotFoundException("Endereço com Id: " + id + " não encontrado"));

            atualizaAddress(enderecoAtual, dto);
            Address enderecoSalvo = addressRepository.save(enderecoAtual);

            return toAddressResponse(enderecoSalvo);
    }

    public void delete(Long id) {
        addressRepository.delete(addressRepository.findById(id).orElseThrow(
                () -> new EnderecoNotFoundException("Endereço com Id: " + id + " não encontrado")));
    }

}
