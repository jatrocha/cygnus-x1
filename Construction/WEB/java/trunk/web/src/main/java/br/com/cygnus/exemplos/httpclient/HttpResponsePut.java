package br.com.cygnus.exemplos.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;

/**
 * Recupera a resposta, configurando o "verbo" http como put.
 */
public class HttpResponsePut extends HttpResponseBase {

   /**
    * @param httpClient {@link HttpClient}.
    * @param type {@link ContentTypeEnum}.
    * @param url {@link String}.
    */
   public HttpResponsePut(final HttpClient httpClient, final ContentTypeEnum type, final String url) {

      super(httpClient, type, url);
   }

   /**
    * @see br.com.cygnus.exemplos.httpclient.HttpResponseBase#getResponse().
    */
   @Override
   protected HttpResponse getResponse() throws ClientProtocolException, IOException {

      HttpPut put = new HttpPut(super.getUrl());

      put.addHeader(HEADER_ACCEPT, super.getContentType().toString());

      return super.getHttpClient().execute(put);

   }

}
