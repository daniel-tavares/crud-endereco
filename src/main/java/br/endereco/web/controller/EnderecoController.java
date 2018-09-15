package br.endereco.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import br.endereco.web.validator.EnderecoValidator;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController extends EnderecoValidator {

	
	@PostMapping(produces="application/json", consumes="application/json")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody Endereco endereco, BindingResult bindind){
		if(bindind.hasErrors())
			return ResponseEntity.badRequest().body(bindind.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(enderecoService.save(endereco), HttpStatus.CREATED);
	}
	
	
	@PutMapping(produces="application/json", consumes="application/json")
	public  @ResponseBody ResponseEntity<?>  update(@RequestBody Endereco endereco){
		String mensagemErro=validarDadosRequisicao(endereco);
		if(!StringUtils.isEmpty(mensagemErro))
			return ResponseEntity.badRequest().body(mensagemErro);
		
		return ResponseEntity.ok().body(enderecoService.save(endereco));
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		String mensagemErro=validarDadosRequisicao(id);
		if(!StringUtils.isEmpty(mensagemErro))
			return ResponseEntity.badRequest().body(mensagemErro);
		
		enderecoService.deleteById(id);
		return ResponseEntity.ok().build();
	} 
	
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") Long id){
       	return new ResponseEntity<>(enderecoService.findById(id), HttpStatus.OK);
	}
	
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Endereco>> findAll(){
       	return new ResponseEntity<>(enderecoService.findAll(), HttpStatus.OK);
	}
}
