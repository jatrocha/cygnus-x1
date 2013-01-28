package br.com.cygnus.exemplos.httpclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StatusEnumTest {

   @Test
   public void testGetByQuandoCodigoInvalido() {

      assertNull(StatusEnum.getBy(0));
   }

   @Test
   public void testGetBy() {

      assertEquals(StatusEnum.OK, StatusEnum.getBy(200));

      assertEquals(Integer.valueOf(200), StatusEnum.OK.getCode());
   }

   @Test
   public void testGetByQuandoStatusRepresentaSucesso() {

      StatusEnum status = StatusEnum.getBy(200);

      assertFalse(status.isAnError());
   }

   @Test
   public void testGetByQuandoStatusRepresentaErro() {

      StatusEnum status = StatusEnum.getBy(404);

      assertTrue(status.isAnError());
   }

}
