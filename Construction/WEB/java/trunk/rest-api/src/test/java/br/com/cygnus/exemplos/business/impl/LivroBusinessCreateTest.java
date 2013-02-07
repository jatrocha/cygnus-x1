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
import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.datastore.LivroDataStore;
import br.com.cygnus.exemplos.persistence.model.Livro;

public class LivroBusinessCreateTest {

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
   public void testCreateQuandoParametroInvalidoNull() {

      new LivroBusiness().create(null);
   }

   @Test
   public void testCreateQuandoErroGeral() {

      final LivroDataStore livroDataStoreMock = this.context.mock(LivroDataStore.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroDataStoreMock).save(this.with(any(Livro.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      LivroBusiness business = new LivroBusiness(livroDataStoreMock);

      try {

         business.create(LivroDTO.buildWith("titulo", "autor", "genero"));

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testCreate() {

      final LivroDataStore livroDataStoreMock = this.context.mock(LivroDataStore.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroDataStoreMock).save(this.with(any(Livro.class)));
         }

      });

      new LivroBusiness(livroDataStoreMock).create(LivroDTO.buildWith("titulo", "autor", "genero"));

      this.context.assertIsSatisfied();
   }
}
