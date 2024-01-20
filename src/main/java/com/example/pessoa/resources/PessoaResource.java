package com.example.pessoa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pessoa.entities.Pessoa;
import com.example.pessoa.services.PessoaService;

@RestController
@RequestMapping("/api/v1")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id){
		Pessoa pessoa = service.findById(id);
		if (pessoa != null) {
			return ResponseEntity.ok(pessoa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
