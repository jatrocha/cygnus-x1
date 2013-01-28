package br.com.cygnus.exemplos.httpclient;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HttpResponsePostTest extends HttpResponseHelper {

   private Mockery context;

   @Before
   public void setup() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void teardown() {

      this.context = null;
   }

   @Test
   public void testGetResponse() throws ClientProtocolException, IOException {

      final HttpClient clientMock = this.context.mock(HttpClient.class);

      this.context.checking(new Expectations() {

         {

            this.one(clientMock).execute(this.with(any(HttpPost.class)));

            this.will(returnValue(HttpResponsePostTest.this.getResponseFake()));
         }

      });

      HttpResponsePost responsePost = new HttpResponsePost(clientMock, ContentTypeEnum.JSON, "http://localhost");

      assertNotNull(responsePost.getResponse());

      this.context.assertIsSatisfied();
   }

}
