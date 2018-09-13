package br.endereco.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.endereco.model.Endereco;
import br.endereco.service.EnderecoService;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping("/form")
	public String saveUpdateFormEndereco(Model model) {
		model.addAttribute("endereco",new Endereco());
		return "form";
	}
	
	@PostMapping(produces="application/json", consumes="application/json")
	public @ResponseBody ResponseEntity<Endereco> save(@RequestBody Endereco endereco){
		return new ResponseEntity<>(enderecoService.save(endereco), HttpStatus.OK);
	}
	
	
	@PutMapping(produces="application/json", consumes="application/json")
	public  @ResponseBody ResponseEntity<Endereco>  update(@RequestBody Endereco endereco){
		return new ResponseEntity<>(enderecoService.save(endereco),HttpStatus.OK);
	}
	
	
	@ResponseStatus(value=HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id){
		enderecoService.deleteById(id);
	} 
	
	
	@GetMapping(produces="application/json", consumes="application/json")
	public @ResponseBody ResponseEntity<List<Endereco>> findAll(){
       	return new ResponseEntity<>(enderecoService.findAll(), HttpStatus.OK);
	}
}
