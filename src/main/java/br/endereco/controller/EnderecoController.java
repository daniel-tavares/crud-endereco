package br.endereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.endereco.model.Endereco;
import br.endereco.service.EnderecoService;

@Controller
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	
	@PostMapping
	public void save(Endereco endereco){
		enderecoService.save(endereco);
	}
	
	
	@PutMapping
	public void update(Endereco endereco){
		enderecoService.update(endereco);
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id){
		enderecoService.delete(id);
	} 
	
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
       	return null;
	}
}
