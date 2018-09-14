package br.endereco.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.endereco.model.Endereco;
import br.endereco.service.EnderecoService;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	
	@PostMapping(produces="application/json", consumes="application/json")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody Endereco endereco, BindingResult bindind){
		
		if(bindind.hasErrors())
			return ResponseEntity.badRequest().body(bindind.getAllErrors().get(0));
		
		return new ResponseEntity<>(enderecoService.save(endereco), HttpStatus.OK);
	}
	
	
	@PutMapping(produces="application/json", consumes="application/json")
	public  @ResponseBody ResponseEntity<Endereco>  update(@RequestBody Endereco endereco){
		return new ResponseEntity<>(enderecoService.save(endereco),HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		enderecoService.deleteById(id);
		return ResponseEntity.ok().build();
	} 
	
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Endereco>> findAll(){
       	return new ResponseEntity<>(enderecoService.findAll(), HttpStatus.OK);
	}
}
