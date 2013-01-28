package br.com.cygnus.exemplos.service.client;

import static helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.JerseyTestBuilder;
import br.com.cygnus.exemplos.business.CarroManipulationBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.resources.CarroManipulacaoResource;

import com.sun.jersey.test.framework.JerseyTest;

public class CarroManipulacaoResourceAdapterTest {

   private final CarroManipulacaoResource resource = new CarroManipulacaoResource();

   private CarroManipulacaoServiceAdapter adapter;

   private final JerseyTest server = JerseyTestBuilder.createJerseyTestBuilder().addResource(this.resource).build();

   private CarroDTO actual = CarroDTO.buildWith(Long.valueOf(1), Marca.FIAT.name(), "modelo", "versao", "motor");

   @Before
   public void init() throws Exception {

      this.adapter = new CarroManipulacaoServiceAdapter();

      this.server.setUp();

      this.adapter.setWebResource(this.server.resource());
   }

   @After
   public void tearDown() throws Exception {

      this.server.tearDown();

   }

   @Test
   public void testCreateQuandoErroGeral() {

      this.resource.setBusiness(new CarroManipulationBusiness() {

         @Override
         public void create(CarroDTO dto) {

            throw new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS);
         }

      });

      try {

         this.adapter.create(new CarroDTO());

         fail("EngineRuntimeException deveria ter sido lancada.");

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }
   }

   @Test
   public void testCreate() {

      this.resource.setBusiness(new CarroManipulationBusiness() {

         @Override
         public void create(CarroDTO dto) {

            CarroManipulacaoResourceAdapterTest.this.actual = dto;
         }

      });

      CarroDTO carro = CarroDTO.buildWith(Long.valueOf(1), Marca.FIAT.name(), "modelo", "versao", "motor");

      this.adapter.create(carro);

      assertEquals(carro.getId(), this.actual.getId());

      assertEquals(carro.getMarca(), this.actual.getMarca());

      assertEquals(carro.getModelo(), this.actual.getModelo());

      assertEquals(carro.getVersao(), this.actual.getVersao());

      assertEquals(carro.getMotor(), this.actual.getMotor());
   }

   @Test
   public void testUpdateQuandoErroGeral() {

      this.resource.setBusiness(new CarroManipulationBusiness() {

         @Override
         public void update(CarroDTO dto) {

            throw new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS);
         }

      });

      try {

         this.adapter.update(new CarroDTO());

         fail("EngineRuntimeException deveria ter sido lancada.");

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }
   }

   @Test
   public void testUpdate() {

      this.resource.setBusiness(new CarroManipulationBusiness() {

         @Override
         public void update(CarroDTO dto) {

            CarroManipulacaoResourceAdapterTest.this.actual = dto;
         }

      });

      CarroDTO carro = CarroDTO.buildWith(Long.valueOf(2), Marca.FIAT.name(), "modelo", "versao", "motor");

      this.adapter.update(carro);

      assertEquals(carro.getId(), this.actual.getId());

      assertEquals(carro.getMarca(), this.actual.getMarca());

      assertEquals(carro.getModelo(), this.actual.getModelo());

      assertEquals(carro.getVersao(), this.actual.getVersao());

      assertEquals(carro.getMotor(), this.actual.getMotor());
   }

   @Test
   public void testDeleteQuandoErroGeral() {

      this.resource.setBusiness(new CarroManipulationBusiness() {

         @Override
         public void delete(CarroDTO dto) {

            throw new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS);
         }

      });

      try {

         this.adapter.delete(CarroDTO.buildWith(Long.valueOf(1)));

         fail("EngineRuntimeException deveria ter sido lancada.");

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }
   }

   @Test
   public void testDelete() {

      this.resource.setBusiness(new CarroManipulationBusiness() {

         @Override
         public void delete(CarroDTO dto) {

         }

      });

      this.adapter.delete(CarroDTO.buildWith(Long.valueOf(1)));
   }

}
