package br.endereco.repository;

import org.springframework.data.repository.CrudRepository;

import br.endereco.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{

}
