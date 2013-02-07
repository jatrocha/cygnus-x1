package br.com.cygnus.exemplos.business.impl;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;
import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.datastore.LivroDataStore;
import br.com.cygnus.exemplos.persistence.model.Livro;

public class LivroBusinessReadTest {

   private Mockery context;

   @Before
   public void init() throws Exception {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void tearDown() throws Exception {

      this.context = null;
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoParametroInvalidoNull() {

      new LivroBusiness().read(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoIdInvalidoNull() {

      new LivroBusiness().read(new LivroFilterDTO());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoIdInvalidoVazio() {

      new LivroBusiness().read(LivroFilterDTO.buildWith(""));
   }

   @Test
   public void testReadQuandoErroGeral() {

      final LivroDataStore livroDataStoreMock = this.context.mock(LivroDataStore.class);

      final String id = "1";

      this.context.checking(new Expectations() {

         {

            this.one(livroDataStoreMock).find(Livro.class, id);

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      LivroBusiness business = new LivroBusiness(livroDataStoreMock);

      try {

         business.read(LivroFilterDTO.buildWith(id));

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testRead() {

      final LivroDataStore livroDataStoreMock = this.context.mock(LivroDataStore.class);

      final String id = "1";

      final Livro livro = new Livro("1", "titulo", "autor", "genero");

      this.context.checking(new Expectations() {

         {

            this.one(livroDataStoreMock).find(Livro.class, id);

            this.will(returnValue(livro));
         }

      });

      LivroDTO livroDTO = new LivroBusiness(livroDataStoreMock).read(LivroFilterDTO.buildWith(id));

      assertEquals(livro.getId(), livroDTO.getId());

      assertEquals(livro.getTitulo(), livroDTO.getTitulo());

      assertEquals(livro.getAutor(), livroDTO.getAutor());

      assertEquals(livro.getGenero(), livroDTO.getGenero());

      this.context.assertIsSatisfied();
   }
}
