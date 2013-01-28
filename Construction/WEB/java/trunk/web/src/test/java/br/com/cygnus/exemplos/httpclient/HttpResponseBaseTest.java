package br.com.cygnus.exemplos.httpclient;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

public class HttpResponseBaseTest extends HttpResponseHelper {

   @Test
   public void testReadContent() {

   }

   @Test(expected = HttpClientHelperRuntimeException.class)
   public void testExecuteErroDeConexaoHostNaoEncontrado() {

      new HttpResponseBase(null, null, null) {

         @Override
         protected HttpResponse getResponse() throws ClientProtocolException, IOException {

            throw new IOException("org.apache.http.conn.HttpHostConnectException: Connection to http://localhost:8088 refused");
         }

      }.execute();
   }

   @Test
   public void testExecuteQuandoStatusOk() {

      ResponseDTO response = new HttpResponseBase(null, null, null) {

         @Override
         protected HttpResponse getResponse() throws ClientProtocolException, IOException {

            return HttpResponseBaseTest.this.getResponseFake();
         }

         @Override
         protected String readContentAsString(InputStream inputStream) {

            return "content";
         }

      }.execute();

      assertEquals(StatusEnum.OK, response.getStatus());

      assertEquals("content", response.getContent());
   }

}
