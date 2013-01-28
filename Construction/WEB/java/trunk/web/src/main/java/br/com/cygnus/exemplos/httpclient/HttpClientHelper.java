package br.com.cygnus.exemplos.httpclient;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import br.com.cygnus.framework.IObjetoGenerico;

/**
 * Utilitario para facilitar a manipulacao de {@link org.apache.http.impl.client.DefaultHttpClient}.
 */
public class HttpClientHelper {

   private String url = null;

   private HttpVerbEnum httpVerb = null;

   private ContentTypeEnum contentType = null;

   /**
    * @param url {@link String} endereco onde encontra-se o servico.
    * @throws IllegalArgumentException caso o parametro <code>url</code> seja <null>null</null> ou == {@link IObjetoGenerico#NULL_STRING}.
    */
   public HttpClientHelper(final String url) {

      super();

      if (StringUtils.isEmpty(url)) {

         throw new IllegalArgumentException();
      }

      this.url = url;
   }

   /**
    * @param verb {@link HttpVerbEnum} que seleciona o "metodo http" a ser utilizado.
    * @return {@link HttpClientHelper}.
    * @throws IllegalArgumentException caso o parametro <code>verb</code> seja <null>null</null>
    */
   public HttpClientHelper add(final HttpVerbEnum verb) {

      if (verb == null) {

         throw new IllegalArgumentException();
      }

      return HttpClientHelper.buildWith(this.url, verb, this.contentType);
   }

   /**
    * @param type {@link ContentTypeEnum} que seleciona o tipo de conteudo que sera aceito como retorno do metodo http.
    * @return {@link HttpClientHelper}.
    * @throws IllegalArgumentException caso o parametro <code>type</code> seja <code>null</code>.
    */
   public HttpClientHelper add(final ContentTypeEnum type) {

      if (type == null) {

         throw new IllegalArgumentException();
      }

      return HttpClientHelper.buildWith(this.url, this.httpVerb, type);
   }

   /**
    * @return {@link ResponseDTO} com o {@link ResponseDTO#getStatus()} e {@link ResponseDTO#getContent()} preenchidos. Em caso de erro,
    *         {@link ResponseDTO#getErro()} estara preenchido.
    * @see {@link ResponseDTO}
    * @throws IllegalArgumentException caso {@link HttpClientHelper#httpVerb} seja <code>null</code>.
    */
   public ResponseDTO execute() {

      if (this.getHttpVerb() == null) {

         throw new IllegalArgumentException();
      }

      HttpClient httpClient = new DefaultHttpClient();

      ResponseDTO response = new HttpResponseFactory(httpClient, this.getContentType()).with(this.getUrl(), this.getHttpVerb()).execute();

      httpClient.getConnectionManager().shutdown();

      return response;
   }

   private static HttpClientHelper buildWith(final String url, final HttpVerbEnum verb, final ContentTypeEnum type) {

      HttpClientHelper helper = new HttpClientHelper(url);

      helper.httpVerb = verb;

      helper.contentType = type;

      return helper;
   }

   /**
    * @return {@link String}.
    */
   protected final String getUrl() {

      return this.url;
   }

   /**
    * @return {@link HttpVerbEnum}.
    */
   protected final HttpVerbEnum getHttpVerb() {

      return this.httpVerb;
   }

   /**
    * @return {@link ContentTypeEnum}.
    */
   protected final ContentTypeEnum getContentType() {

      return this.contentType;
   }

}
