package br.com.cygnus.exemplos.datastore;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;
import br.com.cygnus.framework.template.persistence.DataStore;

/**
 * Manipulação dos {@link LivroDTO} no repositório.
 * 
 * @see LivroRepository
 */
@Service
public class LivroDataStore implements DataStore<Livro> {

   @Autowired
   private LivroRepository repository;

   private MongoTemplate template;

   /**
    * Construtor padrao.
    */
   public LivroDataStore() {

      super();
   }

   /**
    * @param template {@link MongoTemplate}.
    */
   protected LivroDataStore(MongoTemplate template) {

      this();

      this.template = template;
   }

   /**
    * @see br.com.cygnus.framework.template.persistence.DataStore#save(br.com.cygnus.framework.template.dao.entity.AbstractEntity).
    */
   @Override
   public void save(Livro entity) {

      if (entity == null) {

         throw new IllegalArgumentException();
      }

      this.repository.save(entity);
   }

   /**
    * @see br.com.cygnus.framework.template.persistence.DataStore#update(br.com.cygnus.framework.template.dao.entity.AbstractEntity).
    */
   @Override
   public void update(Livro entity) {

      if (entity == null) {

         throw new IllegalArgumentException();
      }

      this.template.save(entity);
   }

   /**
    * @see br.com.cygnus.framework.template.persistence.DataStore#delete(br.com.cygnus.framework.template.dao.entity.AbstractEntity).
    */
   @Override
   public void delete(Livro entity) {

   }

   /**
    * @see br.com.cygnus.framework.template.persistence.DataStore#find(java.lang.Class, java.io.Serializable).
    */
   @Override
   public Livro find(Class<Livro> entityClass, Serializable primaryKey) {

      return this.template.findById(primaryKey, entityClass);
   }

   /**
    * @return all {@link Livro} found otherwise, will return <code>null</code>.
    */
   public List<Livro> findAll() {

      return this.repository.findAll();
   }

}
