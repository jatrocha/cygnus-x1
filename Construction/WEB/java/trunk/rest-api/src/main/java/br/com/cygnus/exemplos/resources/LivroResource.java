package br.com.cygnus.exemplos.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cygnus.exemplos.business.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;

/**
 * Endpoint REST para manipulação de {@link LivroDTO}.
 */
@Component
@Path("/livro")
public class LivroResource {

   @Autowired
   private LivroBusiness livroBusiness;

   /**
    * @return {@link LivroDTO}.
    */
   @GET
   @Path("/query")
   @Produces({ MediaType.APPLICATION_JSON })
   public List<LivroDTO> findAll() {

      return this.livroBusiness.findAll();
   }

   /**
    * @param livroBusiness stub {@link LivroBusiness} para testes unitarios.
    */
   public void setBusiness(LivroBusiness livroBusiness) {

      this.livroBusiness = livroBusiness;
   }

}
