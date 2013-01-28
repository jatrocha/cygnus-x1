package br.com.cygnus.exemplos.httpclient;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

public abstract class HttpResponseBase implements Response {

   /** Cabecalho "accept". */
   protected static final String HEADER_ACCEPT = "accept";

   private final HttpClient httpClient;

   private final ContentTypeEnum contentType;

   private final String url;

   /**
    * @param httpClient {@link HttpClient}.
    * @param type {@link ContentTypeEnum}.
    * @param url {@link String}.
    */
   public HttpResponseBase(final HttpClient httpClient, final ContentTypeEnum type, final String url) {

      super();

      this.httpClient = httpClient;

      this.contentType = type;

      this.url = url;
   }

   /**
    * @see br.com.cygnus.exemplos.httpclient.HttpResponse#execute().
    */
   @Override
   public ResponseDTO execute() {

      try {

         HttpResponse httpResponse = this.getResponse();

         StatusEnum status = StatusEnum.getBy(httpResponse.getStatusLine().getStatusCode());

         String content = this.readContentAsString(httpResponse.getEntity().getContent());

         return ResponseDTO.buildWith(status, content);

      } catch (ClientProtocolException e) {

         throw new HttpClientHelperRuntimeException(e);

      } catch (IOException e) {

         throw new HttpClientHelperRuntimeException(e);
      }
   }

   /**
    * @return {@link HttpResponse}.
    * @throws IOException
    * @throws ClientProtocolException
    */
   protected abstract HttpResponse getResponse() throws ClientProtocolException, IOException;

   /**
    * @param inputStream {@link InputStream} resultado da leitura do da resposta.
    * @return {@link String} conteudo convertido em String.
    */
   protected String readContentAsString(InputStream inputStream) {

      return null;

   }

   /**
    * @return {@link HttpClient}.
    */
   protected final HttpClient getHttpClient() {

      return this.httpClient;
   }

   /**
    * @return {@link ContentTypeEnum}.
    */
   protected final ContentTypeEnum getContentType() {

      return this.contentType;
   }

   /**
    * @return {@link String}.
    */
   protected final String getUrl() {

      return this.url;
   }

}
