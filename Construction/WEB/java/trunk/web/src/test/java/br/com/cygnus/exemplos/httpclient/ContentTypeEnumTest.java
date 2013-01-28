package br.com.cygnus.exemplos.httpclient;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContentTypeEnumTest {

   @Test
   public void testContentTypeEnumToString() {

      assertEquals("application/json", ContentTypeEnum.JSON.toString());

   }

   @Test
   public void testContentTypeEnumValueOf() {

      assertEquals(ContentTypeEnum.JSON, ContentTypeEnum.valueOf("JSON"));
   }

}
