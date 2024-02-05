package com.example.pessoa.services;

import com.example.pessoa.DTOs.AddressResponseDTO;
import com.example.pessoa.DTOs.PessoaResponseDTO;
import com.example.pessoa.DTOs.RequestAddressDTO;
import com.example.pessoa.domain.Address;
import com.example.pessoa.domain.Pessoa;
import com.example.pessoa.repositories.AddressRepository;
import com.example.pessoa.repositories.PessoaRepository;
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

    public List<AddressResponseDTO> findAll(){
        List<Address> address = addressRepository.findAll();
        List<AddressResponseDTO> newAddress = new ArrayList<>();
        List<PessoaResponseDTO> pessoasDTO = new ArrayList<>();
        for (Address endereco : address) {
            AddressResponseDTO addressDTO = new AddressResponseDTO();
            addressDTO.setId(endereco.getId());
            addressDTO.setNomeDaRua(endereco.getNomeDaRua());
            addressDTO.setNumeroDaCasa(endereco.getNumeroDaCasa());
            addressDTO.setBairro(endereco.getBairro());
            addressDTO.setCidade(endereco.getCidade());
            addressDTO.setEstado(endereco.getEstado());


            for (Pessoa pessoa : endereco.getPessoas()) {
                PessoaResponseDTO pessoaDTO = new PessoaResponseDTO();
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

    public Address insert(RequestAddressDTO obj) {
        Address newAddress = new Address(obj);
        addressRepository.save(newAddress);

        Long idPessoa = obj.idPessoa();
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoa.setEndereco(newAddress);
        pessoaRepository.save(pessoa);

        return addressRepository.save(newAddress);
    }

    public Address update(Long id, RequestAddressDTO dto) {
        Address enderecoAtual = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não" +
                " encontrado com ID: " + id));

        updateData(enderecoAtual, dto);

        return addressRepository.save(enderecoAtual);
    }

    private void updateData(Address entity, RequestAddressDTO dto) {
        if (dto.nomeDaRua() != null){
            entity.setNomeDaRua(dto.nomeDaRua());
        }
        if (dto.numeroDaCasa() != null){
            entity.setNumeroDaCasa(dto.numeroDaCasa());
        }
        if (dto.bairro() != null) {
            entity.setBairro(dto.bairro());
        }
        if (dto.cidade() != null) {
            entity.setCidade(dto.cidade());
        }
        if (dto.estado() != null) {
            entity.setEstado(dto.estado());
        }
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

}
