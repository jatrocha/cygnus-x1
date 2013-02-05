package br.com.cygnus.exemplos.service.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.service.RESTServiceAdapter;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * Adaptador de servico <code>REST</code> para consulta de {@link LivroDTO}.
 */
public class LivroServiceAdapter extends RESTServiceAdapter {

   private static final String URI_LIVRO_LIST = "/livro/query";

   /**
    * @return lista contendo todos os {@link LivroDTO} cadastrados.
    */
   public List<LivroDTO> findAll() {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO_LIST).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

      GenericType<List<LivroDTO>> genericType = new GenericType<List<LivroDTO>>(List.class);

      return this.getResponseData(clientResponse, genericType);
   }

}
