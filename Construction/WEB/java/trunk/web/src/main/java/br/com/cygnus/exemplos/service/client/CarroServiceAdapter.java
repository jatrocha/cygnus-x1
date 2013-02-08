package br.com.cygnus.exemplos.service.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.service.RESTServiceAdapter;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * Adaptador de servico <code>REST</code> para consulta de {@link LivroDTO}.
 */
public class CarroServiceAdapter extends RESTServiceAdapter {

   private static final String URI_CARRO = "/carro";

   private static final String URI_CARRO_LIST = URI_CARRO + "/query";

   /**
    * @return lista contendo todos os {@link CarroDTO} cadastrados.
    */
   public List<CarroDTO> findAll() {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_CARRO_LIST).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

      GenericType<List<CarroDTO>> genericType = new GenericType<List<CarroDTO>>() {
      };

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param filter {@link CarroFilterDTO}.
    * @return {@link CarroDTO} recuperado a partir do seu identificador, <code>null</code> caso nao seja encontrado.
    */
   public CarroDTO read(CarroFilterDTO filter) {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_CARRO).queryParam("id", filter.getId().toString())
            .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

      GenericType<CarroDTO> genericType = new GenericType<CarroDTO>() {
      };

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param dto {@link CarroDTO} a ser inserido.
    */
   public void create(CarroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_CARRO).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, dto));
   }

   /**
    * @param dto {@link CarroDTO} a ser atualizado.
    */
   public void update(CarroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_CARRO).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, dto));
   }

}
