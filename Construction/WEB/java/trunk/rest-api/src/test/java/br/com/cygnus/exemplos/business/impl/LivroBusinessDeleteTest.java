package br.com.cygnus.exemplos.business.impl;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static br.com.cygnus.framework.IObjetoGenerico.NULL_STRING;
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
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;

public class LivroBusinessDeleteTest {

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
   public void testDeleteQuandoParametroInvalidoNull() {

      new LivroBusiness().delete(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDeleteQuandoIdInvalidoNull() {

      new LivroBusiness().delete(new LivroDTO());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDeleteQuandoIdInvalidoVazio() {

      new LivroBusiness().delete(LivroDTO.buildWith(NULL_STRING));
   }

   @Test
   public void testDeleteQuandoErroGeral() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      final String id = "1";

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).delete(id);

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      try {

         new LivroBusiness(repositoryMock).delete(LivroDTO.buildWith(id));

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testDelete() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      final String id = "1";

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).delete(id);
         }

      });

      new LivroBusiness(repositoryMock).delete(LivroDTO.buildWith(id));

      this.context.assertIsSatisfied();
   }
}
