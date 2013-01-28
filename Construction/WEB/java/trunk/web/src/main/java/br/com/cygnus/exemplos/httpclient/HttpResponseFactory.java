package br.com.cygnus.exemplos.httpclient;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;

public class HttpResponseFactory {

   private final HttpClient httpClient;

   private final ContentTypeEnum contentType;

   /**
    * @param httpClient {@link HttpClient} cliente para acesso a recursos via http.
    * @param type {@link ContentTypeEnum} que seleciona o tipo de conteudo que sera aceito como retorno do metodo http.
    * @throws IllegalArgumentException caso os parametros <code>httpClient</code> ou <code>type</code> sejam <null>null</null>
    */
   public HttpResponseFactory(HttpClient httpClient, ContentTypeEnum type) {

      super();

      if (httpClient == null && type == null) {

         throw new IllegalArgumentException();
      }

      this.httpClient = httpClient;

      this.contentType = type;
   }

   /**
    * @param url
    * @param httpVerb {@link HttpVerbEnum} que seleciona o "metodo http" a ser utilizado.
    * @return {@link Response};
    * @throws IllegalArgumentException caso os parametros estejam invalidos: <code>null</code> ou vazios.
    */
   public Response with(String url, HttpVerbEnum httpVerb) {

      if (StringUtils.isEmpty(url) || httpVerb == null) {

         throw new IllegalArgumentException();
      }

      if (HttpVerbEnum.POST.equals(httpVerb)) {

         return new HttpResponsePost(this.httpClient, this.contentType, url);
      }

      if (HttpVerbEnum.PUT.equals(httpVerb)) {

         return new HttpResponsePut(this.httpClient, this.contentType, url);
      }

      return null;

   }

}
