package br.endereco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.endereco.model.Endereco;
import br.endereco.repository.EnderecoRepositoryImpl;


@Service
public class EnderecoService {

	@Autowired
	EnderecoRepositoryImpl enderecoRepository;
	
	
	public void save(Endereco endereco){
	   
	}
	
	
	public void update(Endereco endereco){
	
	}
	
	public void delete(Long id){
	}
	
	
	public List<Endereco> findAll(){
		return null;
	}
	
}
