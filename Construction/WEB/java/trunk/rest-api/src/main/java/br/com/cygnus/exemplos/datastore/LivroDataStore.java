package br.com.cygnus.exemplos.datastore;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.framework.template.persistence.DataStore;

@Service
public class LivroDataStore implements DataStore<Livro> {

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

      this.template.insert(entity);
   }

   /**
    * @see br.com.cygnus.framework.template.persistence.DataStore#update(br.com.cygnus.framework.template.dao.entity.AbstractEntity).
    */
   @Override
   public void update(Livro entity) {

      if (entity == null) {

         throw new IllegalArgumentException();
      }

      Query query = new Query(Criteria.where("id").is(entity.getId()));


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

}
