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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.commons.exception.EngineRuntimeException;
import br.com.cygnus.exemplos.helper.InitMongoDB;
import br.com.cygnus.exemplos.persistence.model.Livro;
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
public class LivroBusinessCreateTest extends LivroBusinessTestBase {

   @Resource
   private LivroBusiness business;

   private Mockery context;

   private static MongodExecutable mongodExecutable;
   private static MongodProcess mongodProcess;
   private static Mongo mongo;
   private static MongoTemplate mongoTemplate;

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

   @BeforeClass
   public static void before() throws Exception {

      MongodStarter runtime = MongodStarter.getDefaultInstance();

      mongodExecutable = runtime.prepare(new MongodConfig(Version.V2_0_6, 27017, Network.localhostIsIPv6()));

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
   public void testCreateQuandoParametroInvalidoNull() {

      this.business.create(null);
   }

   @Test
   public void testCreateQuandoErroGeral() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).save(this.with(any(Livro.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      LivroBusiness business = new LivroBusiness(repositoryMock);

      try {

         business.create(this.LIVRO_PARA_INCLUSAO);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testCreate() {

      this.business.create(this.LIVRO_PARA_INCLUSAO);

      assertTrue(Boolean.TRUE);

   }
}
