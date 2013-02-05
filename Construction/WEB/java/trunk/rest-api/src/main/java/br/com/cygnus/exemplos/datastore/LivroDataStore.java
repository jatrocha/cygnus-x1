package br.com.cygnus.exemplos.datastore;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;
import br.com.cygnus.framework.template.persistence.DataStore;

/**
 * Manipulacao dos {@link LivroDTO} no repositorio.
 * 
 * @see LivroRepository
 */
@Service
public class LivroDataStore implements DataStore<Livro> {

   @Autowired
   private LivroRepository repository;

   /**
    * Construtor padrao.
    */
   public LivroDataStore() {

      super();
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

      Livro actual = this.repository.findOne(entity.getId());

      actual.setTitulo(entity.getTitulo());

      actual.setAutor(entity.getAutor());

      actual.setGenero(entity.getGenero());

      this.repository.save(actual);
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

      return this.repository.findOne(primaryKey.toString());
   }

   /**
    * @param repository the repository to set
    */
   protected final void setRepository(LivroRepository repository) {

      this.repository = repository;
   }

   /**
    * @return all {@link Livro} found otherwise, will return <code>null</code>.
    */
   public List<Livro> findAll() {

      return this.repository.findAll();
   }

}
