package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.request.PersonRequestDTO;
import com.example.pessoa.api.dto.request.PersonUpdateRequestDTO;
import com.example.pessoa.api.dto.response.PersonResponseDTO;
import com.example.pessoa.api.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/person")
@RequiredArgsConstructor
public class PersonResource {

	private final PersonServiceImpl service;

	@GetMapping("/private")
	public ResponseEntity<String> privateRoute(Authentication authentication){
		return ResponseEntity.ok("Private route ok! Usuário conectado: " + authentication.getName());
	}

	@GetMapping
	@ResponseStatus(OK)
	public List<PersonResponseDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(OK)
	public PersonResponseDTO findById(@PathVariable Long id){
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public PersonResponseDTO insert(@RequestBody @Valid PersonRequestDTO obj) {
		return service.insert(obj);
		// Retorna "200 OK"
		// return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(OK)
	public PersonResponseDTO update(@PathVariable Long id,
									@RequestBody @Valid PersonUpdateRequestDTO obj){
		return service.update(id, obj);
	}

	/**
	 * Atualiza o endereço associado a uma pessoa identificada pelo ID.
	 * @param idPessoa - Id da pessoa existente.
	 * @param idEndereco - Id do novo endereço.
	 * @return Retorna a entidade Pessoa atualizada com o novo endereço.
	 */
	 @PutMapping("/{idPessoa}/{idEndereco}")
	@ResponseStatus(OK)
	public PersonResponseDTO atualizaPessoa(@PathVariable Long idPessoa,
                                 @PathVariable Long idEndereco) {
		return service.updatePersonAddress(idPessoa, idEndereco);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
