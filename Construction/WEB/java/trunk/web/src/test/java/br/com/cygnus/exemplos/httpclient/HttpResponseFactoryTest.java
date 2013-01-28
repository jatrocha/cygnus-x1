package br.com.cygnus.exemplos.httpclient;

import static org.junit.Assert.assertTrue;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class HttpResponseFactoryTest {

   DefaultHttpClient httpClient = new DefaultHttpClient();

   @Test(expected = IllegalArgumentException.class)
   public void testWithQuandoHttpClientInvalidoNull() {

      HttpClient client = null;

      ContentTypeEnum contentType = null;

      String url = null;

      HttpVerbEnum httpVerb = null;

      new HttpResponseFactory(client, contentType).with(url, httpVerb);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testWithQuandoContentTypeInvalidoNull() {

      HttpClient client = new DefaultHttpClient();

      ContentTypeEnum contentType = null;

      String url = null;

      HttpVerbEnum httpVerb = null;

      new HttpResponseFactory(client, contentType).with(url, httpVerb);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testWithQuandoURLInvalidoNull() {

      HttpClient client = new DefaultHttpClient();

      ContentTypeEnum contentType = ContentTypeEnum.JSON;

      String url = null;

      HttpVerbEnum httpVerb = null;

      new HttpResponseFactory(client, contentType).with(url, httpVerb);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testWithQuandoHttpVerbInvalidoNull() {

      HttpClient client = new DefaultHttpClient();

      ContentTypeEnum contentType = ContentTypeEnum.JSON;

      String url = "URL";

      HttpVerbEnum httpVerb = null;

      new HttpResponseFactory(client, contentType).with(url, httpVerb);
   }

   @Test
   public void testWithQuandoHttpVerbSendoPost() {

      HttpClient client = new DefaultHttpClient();

      ContentTypeEnum contentType = ContentTypeEnum.JSON;

      String url = "URL";

      Response response = new HttpResponseFactory(client, contentType).with(url, HttpVerbEnum.POST);

      assertTrue(response instanceof HttpResponsePost);
   }


   @Test
   public void testWithQuandoHttpVerbSendoPut() {

      HttpClient client = new DefaultHttpClient();

      ContentTypeEnum contentType = ContentTypeEnum.JSON;

      String url = "URL";

      Response response = new HttpResponseFactory(client, contentType).with(url, HttpVerbEnum.PUT);

      assertTrue(response instanceof HttpResponsePut);
   }


}
