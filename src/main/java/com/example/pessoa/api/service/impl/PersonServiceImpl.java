package com.example.pessoa.api.service.impl;

import com.example.pessoa.api.dto.request.PersonRequest;
import com.example.pessoa.api.dto.response.PersonResponse;
import com.example.pessoa.api.entity.Address;
import com.example.pessoa.api.entity.Person;
import com.example.pessoa.api.exception.AddressNotFoundException;
import com.example.pessoa.api.exception.EmailRegisteredExeption;
import com.example.pessoa.api.exception.PersonNotFoundException;
import com.example.pessoa.api.repository.AddressRepository;
import com.example.pessoa.api.repository.PessoaRepository;
import com.example.pessoa.api.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

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
		Person entityPerson = repository.
			findById(id).
			orElseThrow(
				() -> new PersonNotFoundException(id));
		return modelMapper.map(entityPerson, PersonResponse.class);
	}

	public PersonResponse insert(PersonRequest obj) {
		Person entityPerson = modelMapper.map(obj, Person.class);

		if (repository.existsByEmail(entityPerson.getEmail())) {
			throw new EmailRegisteredExeption();
		}
		repository.save(entityPerson);
		return modelMapper.map(entityPerson, PersonResponse.class);
	}

	@Transactional
	public PersonResponse update(Long id, PersonRequest dto){
		Person entity = repository.findById(id).orElseThrow(
				() -> new PersonNotFoundException(id));
		Person dtoToEntity = modelMapper.map(dto, Person.class);
		// metodo "updateData" é um builder que define a lógica de persistência no Banco
		verificarEmailNoBanco(entity, dtoToEntity);
		updateData(entity, modelMapper.map(dtoToEntity, PersonRequest.class));
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
		Person person = repository.findById(idPessoa).orElseThrow(
				() -> new PersonNotFoundException(idPessoa));
		Address address = addressRepository.findById(idEndereco).orElseThrow(
				() -> new PersonNotFoundException(idPessoa));

		for(Person people : address.getPeople()) {
			if (people.getEmail().equals(person.getEmail())){
				throw new EmailRegisteredExeption();
			}
		}

		person.setAddress(address);
		repository.save(person);
		return person;
	}

	private void verificarEmailNoBanco(Person registeredPerson, Person insertPerson){
		if (!(registeredPerson.getEmail().equals(insertPerson.getEmail()))) {
			if (repository.existsByEmailAndIdNot(insertPerson.getEmail(), registeredPerson.getId())) {
				throw new EmailRegisteredExeption();
			}
		}
	}

}
