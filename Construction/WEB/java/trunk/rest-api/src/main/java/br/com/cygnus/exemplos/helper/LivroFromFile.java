package br.com.cygnus.exemplos.helper;

import static br.com.cygnus.framework.IObjetoGenerico.NUMERO_INTEIRO_ZERO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.exemplos.persistence.model.Livro;

/**
 * 
 */
public class LivroFromFile implements LoadFromFile<Livro> {

   private static final int TITULO_POSITION = 1;

   private static final int AUTOR_POSITION = 2;

   private static final int GENERO_POSITION = 3;

   private final BufferedReader reader;

   private String delimiter;

   /**
    * @param reader {@link java.io.BufferedReader} carregado as linhas do arquivo contendo os dados do {@link Carro}.
    * @throws java.lang.IllegalArgumentException caso <code>reader</code> seja <code>null</code>.
    */
   public LivroFromFile(BufferedReader reader) {

      if (reader == null) {

         throw new IllegalArgumentException();
      }

      this.reader = reader;
   }

   /**
    * @see br.com.cygnus.exemplos.helper.LoadFromFile#withDelimiter(java.lang.String).
    */
   @Override
   public LoadFromFile<Livro> withDelimiter(String delimiter) {

      if (StringUtils.isEmpty(delimiter)) {

         throw new IllegalArgumentException();
      }

      this.delimiter = delimiter;

      return this;
   }

   /**
    * @see br.com.cygnus.exemplos.helper.LoadFromFile#load().
    */
   @Override
   public List<Livro> load() {

      List<Livro> list = new ArrayList<Livro>(NUMERO_INTEIRO_ZERO);

      try {

         while (this.reader.ready()) {

            String line = this.reader.readLine();

            list.add(this.parseLine(line));
         }
      } catch (IOException e) {

         throw new EngineRuntimeException();
      }

      return list;

   }

   /**
    * @see br.com.cygnus.exemplos.helper.LoadFromFile#parseLine(java.lang.String).
    */
   @Override
   public Livro parseLine(String line) {

      if (StringUtils.isEmpty(line)) {

         throw new IllegalArgumentException();
      }

      String[] array = line.split(this.delimiter);

      String titulo = array[TITULO_POSITION];

      String autor = StringUtils.isEmpty(array[AUTOR_POSITION]) ? null : array[AUTOR_POSITION];

      String genero = array[GENERO_POSITION];

      return new Livro(null, titulo, autor, genero);
   }
}
