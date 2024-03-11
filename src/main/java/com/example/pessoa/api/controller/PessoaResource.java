package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.request.PessoaRequest;
import com.example.pessoa.api.dto.response.PessoaResponse;
import com.example.pessoa.api.entity.Pessoa;
import com.example.pessoa.api.service.PessoaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Validated
@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class PessoaResource {

	private final PessoaService service;
	
	@GetMapping
	@ResponseStatus(OK)
	public List<PessoaResponse> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(OK)
	public PessoaResponse findById(@PathVariable @NotNull @Positive Long id){
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public PessoaResponse insert(@RequestBody @Valid PessoaRequest obj) {
		return service.insert(obj);
		// Retorna "200 OK"
		// return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(OK)
	public PessoaResponse update(@PathVariable @NotNull @Positive Long id,
						 @RequestBody @Valid PessoaRequest obj){
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
	public Pessoa atualizaPessoa(@PathVariable @NotBlank @Positive Long idPessoa,
								 @PathVariable @NotBlank @Positive Long idEndereco) {
		return service.atualizaPessoaEndereco(idPessoa, idEndereco);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {
		service.delete(id);
	}

}
