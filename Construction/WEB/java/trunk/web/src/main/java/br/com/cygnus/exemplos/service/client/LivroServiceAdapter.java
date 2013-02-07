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

   private static final String URI_LIVRO = "/livro";

   private static final String URI_LIVRO_LIST = URI_LIVRO + "/query";

   /**
    * @return lista contendo todos os {@link LivroDTO} cadastrados.
    */
   public List<LivroDTO> findAll() {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO_LIST).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

      GenericType<List<LivroDTO>> genericType = new GenericType<List<LivroDTO>>() {
      };

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param id {@link String} identificador do {@link LivroDTO}.
    * @return {@link LivroDTO} recuperado a partir do seu identificador, <code>null</code> caso n�o seja encontrado.
    */
   public LivroDTO read(String id) {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO).queryParam("id", id.toString()).accept(MediaType.APPLICATION_JSON)
            .get(ClientResponse.class);

      GenericType<LivroDTO> genericType = new GenericType<LivroDTO>() {
      };

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param dto {@link LivroDTO} a ser inserido.
    */
   public void create(LivroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, dto));
   }

}
