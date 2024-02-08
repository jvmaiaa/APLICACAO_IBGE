package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.entity.Pessoa;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.pessoa.api.mapper.AddressMapper.toAddressEntity;

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
        try {
            Address enderecoEntity = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
            return modelMapper.map(enderecoEntity, AddressResponse.class);
        } catch (RuntimeException e){
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public AddressResponse insert(AddressRequest obj) {

        Pessoa pessoa = pessoaRepository.findById(obj.getIdPessoa()).
                orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Address endereco = toAddressEntity(obj);
        pessoa.setEndereco(endereco);
       // endereco.getPessoas().add(pessoa);
        addressRepository.save(endereco);
        return modelMapper.map(endereco, AddressResponse.class);
    }

    public Address update(Long id, AddressRequest dto) {
        Address enderecoAtual = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não" +
                " encontrado com ID: " + id));

        updateData(enderecoAtual, dto);

        return addressRepository.save(enderecoAtual);
    }

    private void updateData(Address entity, AddressRequest dto) {
        if (dto.getNomeDaRua() != null){
            entity.setNomeDaRua(dto.getNomeDaRua());
        }
        if (dto.getNumeroDaCasa() != null){
            entity.setNumeroDaCasa(dto.getNumeroDaCasa());
        }
        if (dto.getBairro() != null) {
            entity.setBairro(dto.getBairro());
        }
        if (dto.getCidade() != null) {
            entity.setCidade(dto.getCidade());
        }
        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

}
