package br.com.cygnus.exemplos.business.impl;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LivroBusinessUpdateTest extends LivroBusinessTestBase {

   private Mockery context;

   @Resource
   private LivroBusiness business;

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

      this.business.update(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testUpdateQuandoIdInvalidoNull() {

      this.business.update(this.LIVRO_VAZIO);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testUpdateQuandoIdInvalidoVazio() {

      this.business.update(this.LIVRO_COM_ID_VAZIO);
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

         new LivroBusiness(repositoryMock).update(this.LIVRO_PARA_ATUALIZACAO);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() {

      this.business.update(this.LIVRO_PARA_ATUALIZACAO);

      assertTrue(Boolean.TRUE);
   }
}
