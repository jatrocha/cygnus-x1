package br.com.cygnus.exemplos.service.client;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static helper.CarroTestHelper.FIAT;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import helper.CarroTestHelper;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.JerseyTestBuilder;
import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.resources.CarroQueryResource;

import com.sun.jersey.test.framework.JerseyTest;

public class CarroQueryResourceAdapterTest {

   private final CarroQueryResource resource = new CarroQueryResource();

   private CarroQueryServiceAdapter adapter;

   private final JerseyTest server = JerseyTestBuilder.createJerseyTestBuilder().addResource(this.resource).build();

   @Before
   public void init() throws Exception {

      this.adapter = new CarroQueryServiceAdapter();

      this.server.setUp();

      this.adapter.setWebResource(this.server.resource());

   }

   @After
   public void tearDown() throws Exception {

      this.server.tearDown();

   }

   @Test
   public void testFindAll() {

      this.resource.setBusiness(new CarroBusiness() {

         @Override
         public List<CarroDTO> findAll() {

            return CarroTestHelper.listar();

         }

      });

      List<CarroDTO> resultado = this.adapter.list();

      assertNotNull(resultado);

      assertEquals(Integer.valueOf(4), Integer.valueOf(resultado.size()));
   }

   @Test
   public void testFindlAllQuandoNenhumCarroCadastrado() {

      this.resource.setBusiness(new CarroBusiness() {

         @Override
         public List<CarroDTO> findAll() {

            return new ArrayList<CarroDTO>();
         }
      });

      List<CarroDTO> resultado = this.adapter.list();

      assertNotNull(resultado);

      assertTrue(resultado.isEmpty());
   }

   @Test
   public void testReadQuandoErroGeral() {

      this.resource.setBusiness(new CarroBusiness() {

         @Override
         public CarroDTO read(CarroFilterDTO dto) {

            throw new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS);
         }

      });

      try {

         this.adapter.read(FIAT.getId());

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }
   }

   @Test
   public void testRead() {

      this.resource.setBusiness(new CarroBusiness() {

         @Override
         public CarroDTO read(CarroFilterDTO dto) {

            return FIAT;
         }

      });

      CarroDTO actual = this.adapter.read(FIAT.getId());

      assertEquals(FIAT.getId(), actual.getId());
   }

}
