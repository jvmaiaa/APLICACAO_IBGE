package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.PessoaRequest;
import com.example.pessoa.api.dto.response.PessoaResponse;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.entity.Pessoa;
import com.example.pessoa.api.exception.EmailCadastradoExeption;
import com.example.pessoa.api.exception.EnderecoNotFoundException;
import com.example.pessoa.api.exception.PessoaNotFoundException;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

	private static final String EMAIL_CADASTRADO = "E-mail já está cadastrado no sistema";

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public List<PessoaResponse> findAll() {
		return repository.findAll()
			.stream()
			.map(pessoa -> {
				PessoaResponse response = modelMapper.map(pessoa, PessoaResponse.class);
				response.setIdEndereco(pessoa.getEndereco().getId());
				return response;
			}).collect(Collectors.toList());
	}
	
	public PessoaResponse findById(Long id) {
		Pessoa pessoaEntity = repository.
			findById(id).
			orElseThrow(
				() -> new PessoaNotFoundException(id));
		return modelMapper.map(pessoaEntity, PessoaResponse.class);
	}

	public PessoaResponse insert(PessoaRequest obj) {
		Pessoa pessoaEntity = modelMapper.map(obj, Pessoa.class);

		if (repository.existsByEmail(pessoaEntity.getEmail())) {
			throw new EmailCadastradoExeption(EMAIL_CADASTRADO);
		}
		repository.save(pessoaEntity);
		return modelMapper.map(pessoaEntity, PessoaResponse.class);
	}

	@Transactional
	public PessoaResponse update(Long id, PessoaRequest dto){
			Pessoa entity = repository.findById(id).orElseThrow(
					() -> new PessoaNotFoundException(id));
			Pessoa requestToEntity = modelMapper.map(dto, Pessoa.class);
			// metodo "updateData" é um builder que define a lógica de persistência no Banco
			verificarEmailNoBanco(requestToEntity);
			updateData(entity, modelMapper.map(requestToEntity, PessoaRequest.class));
			return modelMapper.map(entity, PessoaResponse.class);
	}

	public void delete(Long id) {
		repository.delete(repository.findById(id).orElseThrow(
				() -> new PessoaNotFoundException(id)));
	}

	// inserir nesse metodo, apenas os campos que poderão ser atualizados
	// Entidade -- DTO
	private void updateData(Pessoa entity, PessoaRequest obj) {
		if (!(entity.getName().equals(obj.getName()))) {
			entity.setName(obj.getName());
		}
		if (!(entity.getAge().equals(obj.getAge()))) {
			entity.setAge(obj.getAge());
		}
		if (!(entity.getEmail().equals(obj.getEmail()))){
			entity.setEmail(obj.getEmail());
		}
		if (!(entity.getEndereco().getId().equals(obj.getIdEndereco()))) {
			entity.setEndereco(addressRepository.findById
				(obj.getIdEndereco()).orElseThrow(
				() -> new EnderecoNotFoundException("Emdereço não encontrado")));
		}
	}

	@Transactional
	public Pessoa atualizaPessoaEndereco(Long idPessoa, Long idEndereco){
		Pessoa pessoa = repository.findById(idPessoa).orElseThrow(
				() -> new PessoaNotFoundException(idPessoa));
		Address endereco = addressRepository.findById(idEndereco).orElseThrow(
				() -> new PessoaNotFoundException(idPessoa));

		for(Pessoa pessoas : endereco.getPessoas()) {
			if (pessoas.getEmail().equals(pessoa.getEmail())){
				throw new EmailCadastradoExeption(EMAIL_CADASTRADO);
			}
		}

		pessoa.setEndereco(endereco);
		repository.save(pessoa);
		return pessoa;
	}

	private void verificarEmailNoBanco(Pessoa pessoa){
		if (repository.existsByEmailAndIdNot(pessoa.getEmail(), pessoa.getId())) {
			throw new EmailCadastradoExeption(EMAIL_CADASTRADO);
		}
	}

}
