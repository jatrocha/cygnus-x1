package br.com.cygnus.exemplos.commons.service;

import java.util.List;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import br.com.cygnus.exemplos.commons.dto.ErrorDTO;
import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.commons.util.PropertiesUtil;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Implementação padrão do Adaptador de serviços REST.
 * 
 * //TODO provavelmente esta classe seja movida para o projeto framework.
 */
public abstract class RESTServiceAdapter {

   protected static final int HTTP_CODE_400 = 400;

   private Client client;

   private WebResource resource;

   /**
    * Construtor padrão.
    */
   public RESTServiceAdapter() {

      ClientConfig clientConfig = new DefaultClientConfig();

      clientConfig.getClasses().add(JacksonJsonProvider.class);

      clientConfig.getProperties().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

      this.client = Client.create(clientConfig);

      this.client.addFilter(new LoggingFilter());
   }

   /**
    * Combina o caminho absoluto da WebResource com o caminho padrão da aplicação, que pode ser encontrado no arquivo de configurações.
    * 
    * @param path {@link String} contendo o caminho do metodo desejado.
    * @return {@link WebResource} configurado.
    */
   public WebResource getWebResourceAbsolutePathRESTAPI(String path) {

      if (this.resource != null) {

         return this.resource.path(path);

      }

      return this.client.resource(PropertiesUtil.getInstance().getString("engine.root.url") + path);

   }

   public WebResource getWebResource(String url) {

      if (this.resource != null) {

         return this.resource.path(url);

      }

      return this.client.resource(url);
   }

   protected <T> T getResponseData(ClientResponse response, GenericType<T> type) {

      if (this.isValidHttpResponse(response)) {

         if (type == null) {

            return null;

         }

         return response.getEntity(type);

      }

      throw this.createRuntimeException(response);
   }

   protected <T> T getResponseData(ClientResponse response, Class<T> clazz) {

      if (this.isValidHttpResponse(response)) {

         if (clazz == null) {

            return null;

         }

         return response.getEntity(clazz);

      }

      throw this.createRuntimeException(response);
   }

   protected void getResponseData(ClientResponse response) {

      if (this.isValidHttpResponse(response)) {

         return;

      }

      throw this.createRuntimeException(response);
   }

   private Boolean isValidHttpResponse(ClientResponse response) {

      return response.getStatus() < HTTP_CODE_400;
   }

   protected EngineRuntimeException createRuntimeException(ClientResponse response) {

      List<ErrorDTO> errors;

      try {

         errors = response.getEntity(this.getGenericTypeForErrorDTO());

      } catch (ClientHandlerException e) {

         return new EngineRuntimeException(e);

      } catch (UniformInterfaceException e) {

         return new EngineRuntimeException(e);

      }

      return new EngineRuntimeException(errors);
   }

   protected GenericType<List<ErrorDTO>> getGenericTypeForErrorDTO() {

      return new GenericType<List<ErrorDTO>>() {
      };
   }

   public void setWebResource(WebResource resource) {

      this.resource = resource;
   }

   public void setClient(Client client) {

      this.client = client;
   }

}
