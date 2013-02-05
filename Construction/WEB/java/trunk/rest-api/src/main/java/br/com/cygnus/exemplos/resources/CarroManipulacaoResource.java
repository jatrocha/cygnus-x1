package br.com.cygnus.exemplos.resources;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.cygnus.exemplos.business.CarroManipulationBusiness;
import br.com.cygnus.exemplos.business.DataManipulation;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;

/**
 * Interface de servico REST para manipulacao de {@link CarroDTO}.
 */
@Component
@Path("/carro")
public class CarroManipulacaoResource {

   @Resource
   private DataManipulation<CarroDTO> business;

   /**
    * @param dto {@link CarroDTO} a ser criado.
    */
   @POST
   @Consumes({ MediaType.APPLICATION_JSON })
   public void create(CarroDTO dto) {

      this.business.create(dto);
   }

   /**
    * @param dto {@link CarroDTO} a ser atualizado.
    */
   @PUT
   @Consumes({ MediaType.APPLICATION_JSON })
   public void update(CarroDTO dto) {

      this.business.update(dto);
   }

   /**
    * @param dto {@link CarroDTO} a ser excluido.
    */
   @DELETE
   @Path("{id}")
   @Consumes({ MediaType.APPLICATION_JSON })
   public void delete(@PathParam("id") Long id) {

      this.business.delete(CarroDTO.buildWith(id));
   }

   /**
    * @param business {@link DataManipulation}.
    */
   public final void setBusiness(CarroManipulationBusiness business) {

      this.business = business;
   }

}
