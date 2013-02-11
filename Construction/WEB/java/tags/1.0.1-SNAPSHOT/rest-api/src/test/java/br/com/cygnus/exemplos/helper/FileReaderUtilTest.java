package br.com.cygnus.exemplos.helper;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Test;

import br.com.cygnus.exemplos.helper.FileReaderUtil;

public class FileReaderUtilTest {

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoArquivoInvalidoNull() {

      String filename = null;

      new FileReaderUtil().read(filename);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoArquivoInvalidoVazio() {

      String filename = "";

      new FileReaderUtil().read(filename);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoArquivInvalido() {

      String filename = "";

      new FileReaderUtil().read(filename);
   }

   @Test
   public void testReadQuandoArquivoInvalido() throws IOException {

      BufferedReader reader = new FileReaderUtil().read("carros.txt");

      assertNotNull(reader);
   }

}
