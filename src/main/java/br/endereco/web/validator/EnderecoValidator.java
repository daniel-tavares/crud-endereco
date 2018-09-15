package br.endereco.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.endereco.model.Endereco;
import br.endereco.service.EnderecoService;

@Component
public class EnderecoValidator {
	
	@Autowired
	protected EnderecoService enderecoService;
	

	public String validarDadosRequisicao(Endereco endereco) {
		return validarDadosRequisicao(endereco.getId());
	}
	
    public String validarDadosRequisicao(Long id) {
		
		if(isIdVazio(id))
			return "Campo id não pode ser nulo na atualizacao";
	         
	    if(!isExisteEntidade(id))
	    	return "Endereco a ser atualizado não existe na base de dados";
	    
	    return null;
	}
	
	private boolean isIdVazio(Long id) {
		return (id==null)?true:false;
	}
	
	private boolean isExisteEntidade(Long id) {
		return (enderecoService.findById(id).isPresent())?true:false;
	}
}
