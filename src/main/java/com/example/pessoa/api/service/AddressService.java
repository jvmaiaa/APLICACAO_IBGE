package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.response.AddressResponse;
import com.example.pessoa.api.dto.response.PessoaResponse;
import com.example.pessoa.api.dto.request.AddressRequest;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.entity.Pessoa;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    private ModelMapper modelMapper;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<AddressResponse> findAll(){
        List<Address> address = addressRepository.findAll();
        List<AddressResponse> newAddress = new ArrayList<>();
        List<PessoaResponse> pessoasDTO = new ArrayList<>();
        for (Address endereco : address) {
            AddressResponse addressDTO = new AddressResponse();
            addressDTO.setId(endereco.getId());
            addressDTO.setNomeDaRua(endereco.getNomeDaRua());
            addressDTO.setNumeroDaCasa(endereco.getNumeroDaCasa());
            addressDTO.setBairro(endereco.getBairro());
            addressDTO.setCidade(endereco.getCidade());
            addressDTO.setEstado(endereco.getEstado());


            for (Pessoa pessoa : endereco.getPessoas()) {
                PessoaResponse pessoaDTO = new PessoaResponse();
                pessoaDTO.setName(pessoa.getName());
                pessoaDTO.setAge(pessoa.getAge());
                pessoaDTO.setEmail(pessoa.getEmail());

                pessoasDTO.add(pessoaDTO);
            }
            addressDTO.setPessoas(pessoasDTO);
            newAddress.add(addressDTO);
        }

        return newAddress;
    }

    public Address findById(Long id){
        return addressRepository.findById(id).orElse(null);
    }

    public Address insert(AddressRequest obj) {
        Address newAddress = new Address(obj);
        addressRepository.save(newAddress);

        Long idPessoa = obj.getIdPessoa();
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoa.setEndereco(newAddress);
        pessoaRepository.save(pessoa);

        return addressRepository.save(newAddress);
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
