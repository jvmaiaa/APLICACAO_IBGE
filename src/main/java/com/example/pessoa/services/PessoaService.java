package com.example.pessoa.services;

import com.example.pessoa.domain.Address;
import com.example.pessoa.domain.Pessoa;
import com.example.pessoa.repositories.AddressRepository;
import com.example.pessoa.repositories.PessoaRepository;
import org.hibernate.collection.spi.PersistentSortedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
		verificarEmailNoBanco(obj);
		return repository.save(obj);
	}

	public Pessoa update(Long id, Pessoa obj){
		Pessoa entity = repository.getReferenceById(id);
		// Criar metodo "updateDate" para criar a logica de atualização do objeto no banco
		verificarEmailNoBanco(obj);
		updateData(entity, obj);
		return repository.save(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
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
