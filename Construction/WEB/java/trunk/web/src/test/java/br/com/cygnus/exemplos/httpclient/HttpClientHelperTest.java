package br.com.cygnus.exemplos.httpclient;

import static br.com.cygnus.framework.IObjetoGenerico.NULL_STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class HttpClientHelperTest {

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

   @Test(expected = IllegalArgumentException.class)
   public void testGetResponseQuandoUrlInvalidoNull() {

      new HttpClientHelper(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testGetResponseQuandoUrlInvalidoVazio() {

      new HttpClientHelper(NULL_STRING);
   }

   @Test
   public void testAddHttpVerb() {

      HttpClientHelper helper = new HttpClientHelper("url");

      HttpVerbEnum verb = HttpVerbEnum.POST;

      HttpClientHelper actual = helper.add(verb);

      assertEquals(verb, actual.getHttpVerb());
   }

   @Test
   public void testAddContentType() {

      HttpClientHelper helper = new HttpClientHelper("url");

      ContentTypeEnum type = ContentTypeEnum.JSON;

      HttpClientHelper actual = helper.add(type);

      assertEquals(type, actual.getContentType());

   }

   @Test
   public void testAddVerbEContentType() {

      HttpClientHelper helper = new HttpClientHelper("url");

      HttpVerbEnum verb = HttpVerbEnum.POST;

      ContentTypeEnum type = ContentTypeEnum.JSON;

      HttpClientHelper actual = helper.add(type).add(verb);

      assertEquals(type, actual.getContentType());

      assertEquals(verb, actual.getHttpVerb());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testExecuteQuandoHttpVerbInvalidoNull() {

      new HttpClientHelper("url").add(ContentTypeEnum.JSON).execute();
   }

   @Test
   @Ignore
   public void testExecute() {

      String url = "URL";

      ResponseDTO response = new HttpClientHelper(url).add(HttpVerbEnum.POST).add(ContentTypeEnum.JSON).execute();

      assertEquals(StatusEnum.OK, response.getStatus());

      assertNotNull(response.getContent());
   }
}
