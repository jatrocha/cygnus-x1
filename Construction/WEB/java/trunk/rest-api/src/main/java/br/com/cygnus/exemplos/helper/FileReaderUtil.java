package br.com.cygnus.exemplos.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

/**
 * Facilitador para leitura com buffer de um arquivo no sistema de arquivos.
 */
public class FileReaderUtil {

   /**
    * @param filename {@link String} contendo o nome do arquivo a ser lido. Caso n‹o seja informado o arquivo seja invalido,
    *        {@link java.lang.IllegalArgumentException} sera lancada.
    * @return {@link java.io.BufferedReader} com o arquivo.
    */
   public final BufferedReader read(final String filename) {

      if (StringUtils.isEmpty(filename)) {

         throw new IllegalArgumentException();
      }

      InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);

      InputStreamReader isr = new InputStreamReader(is);

      return new BufferedReader(isr);
   }

}
