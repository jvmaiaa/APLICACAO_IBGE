package com.example.pessoa.resources;

import com.example.pessoa.domain.Pessoa;
import com.example.pessoa.services.PessoaService;
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
	public List<Pessoa>findAll() {
		List<Pessoa> list = service.findAll();
		return list;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(OK)
	public Pessoa findById(@PathVariable Long id){
			Pessoa pessoa = service.findById(id);
			return pessoa;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Pessoa insert(@RequestBody Pessoa obj) {
		obj = service.insert(obj);
		// Retorna "201 CREATED" com a url do objeto criado
		return obj;
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
