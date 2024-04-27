package com.example.pessoa.api.service.impl;

import com.example.pessoa.api.dto.request.PersonRequestDTO;
import com.example.pessoa.api.dto.request.PersonUpdateRequestDTO;
import com.example.pessoa.api.dto.response.PersonResponseDTO;
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

	@Override
	public List<PersonResponseDTO> findAll() {
		return repository.findAll()
			.stream()
			.map(person -> {
				PersonResponseDTO response = modelMapper.map(person, PersonResponseDTO.class);
				response.setIdAddress(person.getAddress().getId());
				return response;
			}).collect(Collectors.toList());
	}

	@Override
	public PersonResponseDTO findById(Long id) {
		Person entityPerson = repository.
			findById(id).
			orElseThrow(
				() -> new PersonNotFoundException(id));
		return modelMapper.map(entityPerson, PersonResponseDTO.class);
	}

	@Override
	public PersonResponseDTO insert(PersonRequestDTO obj) {
		Person entityPerson = modelMapper.map(obj, Person.class);

		if (repository.existsByEmail(entityPerson.getEmail())) {
			throw new EmailRegisteredExeption();
		}
		repository.save(entityPerson);
		return modelMapper.map(entityPerson, PersonResponseDTO.class);
	}

	@Override
	@Transactional
	public PersonResponseDTO update(Long id, PersonUpdateRequestDTO dto){
		Person entity = repository.findById(id).orElseThrow(
				() -> new PersonNotFoundException(id));
		Person DTO = modelMapper.map(dto, Person.class);
		// metodo "updateData" é um builder que define a lógica de persistência no Banco
		checkEmailAtTheBank(entity, DTO);
		updateData(entity, modelMapper.map(DTO, PersonUpdateRequestDTO.class));
		repository.save(entity);
		return modelMapper.map(entity, PersonResponseDTO.class);
	}

	@Override
	public void delete(Long id) {
		repository.delete(repository.findById(id)
				.orElseThrow( () -> new PersonNotFoundException(id) ));
	}

	// inserir nesse metodo, apenas os campos que poderão ser atualizados
	private void updateData(Person entity, PersonUpdateRequestDTO dto) {
		if (!(entity.getName().equals(dto.getName())) && dto.getName() != null) {
			entity.setName(dto.getName());
		}
		if (!(entity.getAge().equals(dto.getAge())) && dto.getAge() != null) {
			entity.setAge(dto.getAge());
		}
		if (!(entity.getEmail().equals(dto.getEmail())) && dto.getEmail() != null){
			entity.setEmail(dto.getEmail());
		}
		if (!(entity.getAddress().getId().equals(dto.getIdAddress())) && dto.getIdAddress() != null) {
			entity.setAddress(addressRepository.findById(dto.getIdAddress())
					.orElseThrow( () -> new AddressNotFoundException(dto.getIdAddress()) ));
		}
	}

	@Override
	@Transactional
	public PersonResponseDTO updatePersonAddress(Long personId, Long AddressId){
		Person person = repository.findById(personId).orElseThrow(
				() -> new PersonNotFoundException(personId));
		Address address = addressRepository.findById(AddressId).orElseThrow(
				() -> new PersonNotFoundException(personId));

		for(Person people : address.getPeople()) {
			if (people.getEmail().equals(person.getEmail())){
				throw new EmailRegisteredExeption();
			}
		}

		person.setAddress(address);
		repository.save(person);
		return modelMapper.map(person, PersonResponseDTO.class);
	}

	private void checkEmailAtTheBank(Person registeredPerson, Person insertPerson){
		if (!(registeredPerson.getEmail().equals(insertPerson.getEmail()))) {
			if (repository.existsByEmailAndIdNot(insertPerson.getEmail(), registeredPerson.getId())) {
				throw new EmailRegisteredExeption();
			}
		}
	}

}
