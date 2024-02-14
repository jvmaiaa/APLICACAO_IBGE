package com.example.pessoa.api.controller;

import com.example.pessoa.api.dto.request.PessoaRequest;
import com.example.pessoa.api.dto.response.PessoaResponse;
import com.example.pessoa.api.entity.Pessoa;
import com.example.pessoa.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/v1")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	@GetMapping
	@ResponseStatus(OK)
	public List<PessoaResponse> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(OK)
	public PessoaResponse findById(@PathVariable Long id){
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public PessoaResponse insert(@RequestBody PessoaRequest obj) {
		// Retorna "201 CREATED" com a url do objeto criado
		return service.insert(obj);
		// Retorna "200 OK"
		// return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(OK)
	public Pessoa update(@PathVariable Long id, @RequestBody Pessoa obj){
		obj = service.update(id, obj);
		return obj;
	}

	// atualizar o enreço de pessoa, apenas passando o ID do endereço na url
	@PutMapping("/{idPessoa}/{idEndereco}")
	@ResponseStatus(OK)
	public Pessoa atualizaPessoa(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
		return service.atualizaPessoaEndereco(idPessoa, idEndereco);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
