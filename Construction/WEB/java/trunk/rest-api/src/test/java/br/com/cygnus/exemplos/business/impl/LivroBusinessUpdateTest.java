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
import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;

public class LivroBusinessUpdateTest {

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
   public void testUpdateQuandoParametroInvalidoNull() {

      new LivroBusiness().update(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testUpdateQuandoIdInvalidoNull() {

      new LivroBusiness().update(new LivroDTO());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testUpdateQuandoIdInvalidoVazio() {

      new LivroBusiness().update(LivroDTO.buildWith(NULL_STRING));
   }

   @Test
   public void testUpdateQuandoErroGeral() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).save(this.with(any(Livro.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      try {

         new LivroBusiness(repositoryMock).update(LivroDTO.buildWith("1", "titulo", "autor", "genero"));

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).save(this.with(any(Livro.class)));
         }

      });

      new LivroBusiness(repositoryMock).create(LivroDTO.buildWith("1", "titulo", "autor", "genero"));

      this.context.assertIsSatisfied();
   }
}
