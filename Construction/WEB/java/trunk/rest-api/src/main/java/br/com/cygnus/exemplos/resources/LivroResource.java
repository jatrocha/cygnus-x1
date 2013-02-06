package br.com.cygnus.exemplos.resources;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;

/**
 * Endpoint REST para manipula��o de {@link LivroDTO}.
 */
@Component
@Path("/livro")
public class LivroResource {

   @Resource
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
    * @return {@link LivroDTO}.
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public LivroDTO findBy(@QueryParam("id") String id) {

      return this.livroBusiness.read(LivroFilterDTO.buildWith(id));
   }

   /**
    * @param livroBusiness stub {@link LivroBusiness} para testes unitarios.
    */
   public void setBusiness(LivroBusiness livroBusiness) {

      this.livroBusiness = livroBusiness;
   }

}
