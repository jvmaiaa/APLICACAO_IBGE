package com.example.pessoa.api.service;

import com.example.pessoa.api.dto.request.PersonRequest;
import com.example.pessoa.api.dto.response.PersonResponse;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.entity.Person;
import com.example.pessoa.api.exception.EmailRegisteredExeption;
import com.example.pessoa.api.exception.AddressNotFoundException;
import com.example.pessoa.api.exception.PersonNotFoundException;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PessoaRepository repository;

	private  final AddressRepository addressRepository;

	private final ModelMapper modelMapper;
	
	public List<PersonResponse> findAll() {
		return repository.findAll()
			.stream()
			.map(person -> {
				PersonResponse response = modelMapper.map(person, PersonResponse.class);
				response.setIdAddress(person.getAddress().getId());
				return response;
			}).collect(Collectors.toList());
	}
	
	public PersonResponse findById(Long id) {
		Person pessoaEntity = repository.
			findById(id).
			orElseThrow(
				() -> new PersonNotFoundException(id));
		return modelMapper.map(pessoaEntity, PersonResponse.class);
	}

	public PersonResponse insert(PersonRequest obj) {
		Person pessoaEntity = modelMapper.map(obj, Person.class);

		if (repository.existsByEmail(pessoaEntity.getEmail())) {
			throw new EmailRegisteredExeption();
		}
		repository.save(pessoaEntity);
		return modelMapper.map(pessoaEntity, PersonResponse.class);
	}

	@Transactional
	public PersonResponse update(Long id, PersonRequest dto){
		Person entity = repository.findById(id).orElseThrow(
				() -> new PersonNotFoundException(id));
		Person requestToEntity = modelMapper.map(dto, Person.class);
		// metodo "updateData" é um builder que define a lógica de persistência no Banco
		verificarEmailNoBanco(entity, requestToEntity);
		updateData(entity, modelMapper.map(requestToEntity, PersonRequest.class));
		repository.save(entity);
		return modelMapper.map(entity, PersonResponse.class);
	}

	public void delete(Long id) {
		repository.delete(repository.findById(id)
				.orElseThrow( () -> new PersonNotFoundException(id) ));
	}

	// inserir nesse metodo, apenas os campos que poderão ser atualizados
	// Entidade -- DTO
	private void updateData(Person entity, PersonRequest obj) {
		if (!(entity.getName().equals(obj.getName()))) {
			entity.setName(obj.getName());
		}
		if (!(entity.getAge().equals(obj.getAge()))) {
			entity.setAge(obj.getAge());
		}
		if (!(entity.getEmail().equals(obj.getEmail()))){
			entity.setEmail(obj.getEmail());
		}
		if (!(entity.getAddress().getId().equals(obj.getIdAddress()))) {
			entity.setAddress(addressRepository.findById
				(obj.getIdAddress()).orElseThrow(
				() -> new AddressNotFoundException(obj.getIdAddress())));
		}
	}

	@Transactional
	public Person atualizaPessoaEndereco(Long idPessoa, Long idEndereco){
		Person pessoa = repository.findById(idPessoa).orElseThrow(
				() -> new PersonNotFoundException(idPessoa));
		Address endereco = addressRepository.findById(idEndereco).orElseThrow(
				() -> new PersonNotFoundException(idPessoa));

		for(Person pessoas : endereco.getPeople()) {
			if (pessoas.getEmail().equals(pessoa.getEmail())){
				throw new EmailRegisteredExeption();
			}
		}

		pessoa.setAddress(endereco);
		repository.save(pessoa);
		return pessoa;
	}

	private void verificarEmailNoBanco(Person pessoaDoBanco, Person pessoaDaRequest){
		if (!(pessoaDoBanco.getEmail().equals(pessoaDaRequest.getEmail()))) {
			if (repository.existsByEmailAndIdNot(pessoaDaRequest.getEmail(), pessoaDoBanco.getId())) {
				throw new EmailRegisteredExeption();
			}
		}
	}

}
