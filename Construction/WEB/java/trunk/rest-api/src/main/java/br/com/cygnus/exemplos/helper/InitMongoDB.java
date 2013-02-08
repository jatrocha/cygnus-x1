package br.com.cygnus.exemplos.helper;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.cygnus.exemplos.persistence.model.Livro;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}.
 */
public class InitMongoDB {

   @Autowired
   private MongoTemplate mongoTemplate;

   /**
    * Initializes and loads data on the database.
    */
   public void init() {

      // Drop existing collections
      this.mongoTemplate.dropCollection("livro");

      List<Livro> livros = new LivroFromFile(new FileReaderUtil().read("livros.txt")).withDelimiter("#").load();

      for (Livro livro : livros) {

         livro.setId(UUID.randomUUID().toString());

         this.mongoTemplate.save(livro);
      }
   }
}
