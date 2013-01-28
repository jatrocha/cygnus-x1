package br.com.cygnus.exemplos.service.client;

import javax.ws.rs.core.MediaType;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.service.RESTServiceAdapter;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Adaptador de servico <code>REST</code> para consulta de {@link CarroDTO}.
 */
public class CarroManipulacaoServiceAdapter extends RESTServiceAdapter {

   private static final String URI_CARRO = "/carro";

   /**
    * @param dto {@link CarroDTO} a ser inserido.
    */
   public void create(CarroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_CARRO).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, dto));
   }

   /**
    * @param dto @param dto {@link CarroDTO} a ser atualizado.
    */
   public void update(CarroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_CARRO).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, dto));
   }

   /**
    * @param dto @param dto {@link CarroDTO} a ser excluido.
    */
   public void delete(CarroDTO dto) {

      String path = URI_CARRO.concat("/".concat(dto.getId().toString()));

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(path).delete(ClientResponse.class));
   }

}
