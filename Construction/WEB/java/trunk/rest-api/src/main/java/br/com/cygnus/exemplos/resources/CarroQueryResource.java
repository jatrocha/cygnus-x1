package br.com.cygnus.exemplos.resources;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.cygnus.exemplos.business.DataQuery;
import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;

@Component
@Path("/carro/query")
public class CarroQueryResource {

   @Resource(name = "carroBusiness")
   private CarroBusiness business;

   /**
    * @return {@link CarroDTO}.
    */
   @PUT
   @Produces({ MediaType.APPLICATION_JSON })
   public List<CarroDTO> listar() {

      return this.business.findAll();
   }

   /**
    * @param id
    * @return
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public CarroDTO read(@QueryParam("id") Long id) {

      return this.business.read(CarroFilterDTO.buildWith(id));
   }

   /**
    * @param business {@link DataQuery}.
    */
   public final void setBusiness(CarroBusiness business) {

      this.business = business;
   }

}
