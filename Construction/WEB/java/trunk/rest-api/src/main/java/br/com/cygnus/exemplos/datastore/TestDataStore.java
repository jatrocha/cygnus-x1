package br.com.cygnus.exemplos.datastore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;

@Service
public class TestDataStore {

   @Autowired
   private LivroRepository repository;

   public void teste() {

      this.repository.save(new Livro("1", "titulo", "autor", "genero"));

   }

}
