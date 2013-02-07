package br.com.cygnus.exemplos.resources;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;

/**
 * Endpoint REST para manipulação de {@link LivroDTO}.
 */
@Component
@Path("/carro")
public class CarroResource {

   @Resource(name = "carroBusiness")
   private CarroBusiness business;

   /**
    * @return Lista de todos os {@link CarroDTO}s cadastrados.
    */
   @GET
   @Path("/query")
   @Produces({ MediaType.APPLICATION_JSON })
   public List<CarroDTO> findAll() {

      return this.business.findAll();
   }

   /**
    * @param carroBusiness stub {@link CarroBusiness} para testes unitarios.
    */
   public void setBusiness(CarroBusiness carroBusiness) {

      this.business = carroBusiness;
   }

}
