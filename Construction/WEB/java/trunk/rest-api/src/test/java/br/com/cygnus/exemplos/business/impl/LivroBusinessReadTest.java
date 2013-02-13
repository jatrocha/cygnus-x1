package br.com.cygnus.exemplos.business.impl;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.helper.InitMongoDB;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LivroBusinessReadTest extends LivroBusinessTestBase {

   @Resource
   private LivroBusiness business;

   private Mockery context;

   private static MongodExecutable mongodExecutable;
   private static MongodProcess mongodProcess;
   private static Mongo mongo;
   private static MongoTemplate mongoTemplate;

   @Before
   public void init() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void tearDown() {

      this.context = null;
   }

   @BeforeClass
   public static void before() throws Exception {

      MongodStarter runtime = MongodStarter.getDefaultInstance();

      mongodExecutable = runtime.prepare(new MongodConfig(Version.V2_2_1, 27017, Network.localhostIsIPv6()));

      mongodProcess = mongodExecutable.start();

      mongo = new Mongo("localhost", 27017);

      mongoTemplate = new MongoTemplate(mongo, "exemplos");

      new InitMongoDB(mongoTemplate).init();
   }

   @AfterClass
   public static void destroy() throws Exception {

      mongodProcess.stop();

      mongodExecutable.stop();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoParametroInvalidoNull() {

      this.business.read(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoIdInvalidoNull() {

      this.business.read(this.LIVRO_FILTER_VAZIO);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoIdInvalidoVazio() {

      this.business.read(this.LIVRO_FILTER_ID_VAZIO);
   }

   @Test
   public void testReadQuandoErroGeral() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).findOne(LivroBusinessReadTest.this.ID);

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      LivroBusiness business = new LivroBusiness(repositoryMock);

      try {

         business.read(this.LIVRO_FILTER_COM_ID);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testRead() {

      LivroDTO livroDTO = this.business.read(this.LIVRO_FILTER_COM_ID);

      assertEquals(this.LIVRO_PARA_LEITURA.getId(), livroDTO.getId());

      assertEquals(this.LIVRO_PARA_LEITURA.getTitulo(), livroDTO.getTitulo());

      assertEquals(this.LIVRO_PARA_LEITURA.getAutor(), livroDTO.getAutor());

      assertEquals(this.LIVRO_PARA_LEITURA.getGenero(), livroDTO.getGenero());
   }
}
