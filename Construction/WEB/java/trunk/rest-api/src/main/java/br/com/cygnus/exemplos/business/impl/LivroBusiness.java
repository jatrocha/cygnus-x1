package br.com.cygnus.exemplos.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.business.DataManipulation;
import br.com.cygnus.exemplos.business.DataQuery;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;
import br.com.cygnus.exemplos.datastore.LivroDataStore;
import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.framework.template.business.converter.Converter;

/**
 * Manipulacao de {@link Livro}s.
 */
@Service
public class LivroBusiness implements DataQuery<LivroFilterDTO, LivroDTO>, DataManipulation<LivroDTO> {

   @Resource
   private LivroDataStore dataStore;

   /**
    * Construtor padrao.
    */
   public LivroBusiness() {

      super();
   }

   /**
    * @param dataStore {@link LivroDataStore}.
    */
   protected LivroBusiness(final LivroDataStore dataStore) {

      this();

      this.dataStore = dataStore;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#read(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   public LivroDTO read(LivroFilterDTO dto) {

      if (dto == null || StringUtils.isEmpty(dto.getId())) {

         throw new IllegalArgumentException();
      }

      return new LivroToLivroDTOConverter().convert(this.dataStore.find(Livro.class, dto.getId()));
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findBy(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   public List<LivroDTO> findBy(LivroFilterDTO dto) {
      return null;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#create(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void create(LivroDTO dto) {

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#update(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void update(LivroDTO dto) {

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#delete(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void delete(LivroDTO dto) {

   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findAll().
    */
   @Override
   public List<LivroDTO> findAll() {

      return new ListLivroToListLivroDTOConverter().convert(this.dataStore.findAll());
   }

   private class LivroToLivroDTOConverter implements Converter<Livro, LivroDTO> {

      /**
       * @see br.com.cygnus.framework.template.business.converter.Converter#convert(java.lang.Object).
       */
      @Override
      public LivroDTO convert(Livro source) {

         return LivroDTO.buildWith(source.getId(), source.getTitulo(), source.getAutor(), source.getGenero());
      }
   }

   private class ListLivroToListLivroDTOConverter implements Converter<List<Livro>, List<LivroDTO>> {

      /**
       * @see br.com.cygnus.framework.template.business.converter.Converter#convert(java.lang.Object)
       */
      @Override
      public List<LivroDTO> convert(List<Livro> source) {

         List<LivroDTO> retorno = new ArrayList<LivroDTO>();

         for (Livro livro : source) {

            retorno.add(new LivroToLivroDTOConverter().convert(livro));
         }
         return retorno;
      }
   }
}
