package br.com.cygnus.exemplos.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFiltroDTO;
import br.com.cygnus.exemplos.datastore.LivroDataStore;
import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.framework.template.business.converter.Converter;

/**
 * Business for {@link Livro}.
 */
@Service
public class LivroBusiness /* implements LivroService */{

   @Resource
   private LivroDataStore dataStore;

   public LivroDTO read(LivroFiltroDTO dto) {

      return null;
   }

   public List<LivroDTO> findBy(LivroFiltroDTO dto) {
      return null;
   }

   public void create(LivroDTO dto) {

   }

   public void update(LivroDTO dto) {

   }

   public void delete(LivroDTO dto) {

   }

   public List<LivroDTO> findAll() {

      return new Converter<List<Livro>, List<LivroDTO>>() {

         /**
          * @param source {@link Livro} list with all entities found on the datastore
          * @return {@link LivroDTO} converted. An empty {@link java.util.List} if no entity found.
          */
         @Override
         public List<LivroDTO> convert(List<Livro> source) {

            List<LivroDTO> retorno = new ArrayList<LivroDTO>();

            for (Livro livro : source) {

               retorno.add(LivroDTO.buildWith(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getGenero()));
            }

            return retorno;
         }
      }.convert(this.dataStore.findAll());
   }

}
