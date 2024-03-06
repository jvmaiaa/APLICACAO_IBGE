package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.PessoaRequest;
import com.example.pessoa.api.dto.response.PessoaResponse;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.entity.Pessoa;
import com.example.pessoa.api.exception.EmailCadastradoExeption;
import com.example.pessoa.api.exception.PessoaNotFoundException;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

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
				() -> new PessoaNotFoundException("Pessoa com id " + id + " não encontrada"));
		return modelMapper.map(pessoaEntity, PessoaResponse.class);
	}

	public PessoaResponse insert(PessoaRequest obj) {
		Pessoa pessoaEntity = modelMapper.map(obj, Pessoa.class);

		if (repository.existsByEmail(pessoaEntity.getEmail())) {
			throw new EmailCadastradoExeption("E-mail já está cadastrado");
		}
		repository.save(pessoaEntity);
		return modelMapper.map(pessoaEntity, PessoaResponse.class);
	}

	public Pessoa update(Long id, Pessoa obj){
		try {
			Pessoa entity = repository.findById(id).orElseThrow(
					() -> new PessoaNotFoundException("Pessoa com id " + id + " não encontrada"));
			;
			// Criar metodo "updateDate" para criar a logica de atualização do objeto no banco
			verificarEmailNoBanco(obj);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (PessoaNotFoundException e){
			throw new PessoaNotFoundException("Pessoa com id " + id + " não encontrada");
		}
	}

	public void delete(Long id) {
		repository.delete(repository.findById(id).orElseThrow(
				() -> new PessoaNotFoundException("Pessoa com id " + id + " não encontrada")));
	}

	// Colocar nesse metodo, apenas os campos que poderão ser atualizados
	private void updateData(Pessoa entity, Pessoa obj) {
		entity.setName(obj.getName());
		entity.setAge(obj.getAge());
		entity.setEmail(obj.getEmail());
	}

	public Pessoa atualizaPessoaEndereco(Long idPessoa, Long idEndereco){
		Pessoa pessoa = repository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Id de pessoa não encontrado!"));
		Address endereco = addressRepository.findById(idEndereco).orElseThrow(() -> new RuntimeException("Id de endereço não encontrado!"));

		for(Pessoa pessoas : endereco.getPessoas()) {
			if (pessoas.getEmail().equals(pessoa.getEmail())){
				throw new RuntimeException("Email já existe naquele endereço!");
			}
		}

		pessoa.setEndereco(endereco);
		repository.save(pessoa);
		return pessoa;
	}

	public void verificarEmailNoBanco(Pessoa pessoa){
		try {
			List<Pessoa> pessoaNoBanco = repository.findAll();

			for (Pessoa persons : pessoaNoBanco) {
				if (persons.getEmail().equals(pessoa.getEmail())) {
					throw new RuntimeException("E-mail já existente!");
				}
			}
		} catch (RuntimeException e){
			throw new RuntimeException("Erro ao cadastrar e-mail");
		}
	}

}
