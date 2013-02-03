package br.com.cygnus.exemplos.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cygnus.exemplos.persistence.model.Livro;

public interface LivroRepository extends MongoRepository<Livro, String>{

}
