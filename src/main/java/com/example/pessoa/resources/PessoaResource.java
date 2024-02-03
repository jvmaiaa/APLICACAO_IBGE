package com.example.pessoa.resources;

import com.example.pessoa.domain.Pessoa;
import com.example.pessoa.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id){
		try {
			Pessoa pessoa = service.findById(id);
			return ResponseEntity.ok(pessoa);
		} catch (ResponseStatusException e){
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
	}

	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody Pessoa obj) {
		obj = service.insert(obj);
		// Retorna "201 CREATED" com a url do objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		// Retorna "200 OK"
		// return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	// atualizar o enreço de pessoa, apenas passando o ID do enreço na url
	@PutMapping("/{idPessoa}/{idEndereco}")
	public Pessoa atualizaPessoa(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
		return service.atualizaPessoaEndereco(idPessoa, idEndereco);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
