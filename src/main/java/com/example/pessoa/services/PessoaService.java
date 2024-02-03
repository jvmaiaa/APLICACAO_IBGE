package com.example.pessoa.services;

import com.example.pessoa.domain.Address;
import com.example.pessoa.domain.Pessoa;
import com.example.pessoa.repositories.AddressRepository;
import com.example.pessoa.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private AddressRepository addressRepository;
	
	public List<Pessoa> findAll() {
		return repository.findAll();
	}
	
	public Pessoa findById(Long id) {
		return repository.findById(id)
				.orElseThrow( () ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Pessoa não encontrada"));
	}
	
	public Pessoa insert(Pessoa obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Pessoa update(Long id, Pessoa obj){
		Pessoa entity = repository.getReferenceById(id);
		// Criar metodo "updateDate" para criar a logica de atualização do objeto no banco
		updateData(entity, obj);
		return repository.save(entity);
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

		pessoa.setEndereco(endereco);
		repository.save(pessoa);
		return pessoa;
	}


} 
