package br.gov.caixa.portalbanking.service.impl;


@Serrvice
public class EnderecoRepository {

	@Autowaride
	EnderecoRepository enderecoRepository;
	
	
	public void save(Endereco endereco){
	    this.enderecoRepository.save(endereco);	
	}
	
	
	public void update(Endereco endereco){
	    this.enderecoRepository.update(endereco);	
	}
	
	public void delete(int id){
	    this.enderecoRepository.delete(id);	
	}
	
}
