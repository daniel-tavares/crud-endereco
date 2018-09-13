package br.endereco.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.endereco.model.Endereco;
import br.endereco.repository.EnderecoRepository;


@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	
	public Endereco save(Endereco endereco){
		return enderecoRepository.save(endereco);
	}

	public void deleteById(Long id){
		enderecoRepository.deleteById(id);
	}
	
	public List<Endereco> findAll(){
		List<Endereco> listaEndereco=new ArrayList<>();
		enderecoRepository.findAll().forEach(listaEndereco::add);
		return listaEndereco; 
	}
	
}
