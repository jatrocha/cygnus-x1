package br.com.cygnus.exemplos.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * Recupera a resposta, configurando o "verbo" http como post.
 */
public class HttpResponsePost extends HttpResponseBase {

   /**
    * @param httpClient {@link HttpClient}.
    * @param type {@link ContentTypeEnum}.
    * @param url {@link String}.
    */
   public HttpResponsePost(final HttpClient httpClient, final ContentTypeEnum type, final String url) {

      super(httpClient, type, url);
   }

   /**
    * @see br.com.cygnus.exemplos.httpclient.HttpResponseBase#getResponse()
    */
   @Override
   protected HttpResponse getResponse() throws ClientProtocolException, IOException {

      HttpPost post = new HttpPost(super.getUrl());

      post.addHeader(HEADER_ACCEPT, super.getContentType().toString());

      return super.getHttpClient().execute(post);
   }

}
