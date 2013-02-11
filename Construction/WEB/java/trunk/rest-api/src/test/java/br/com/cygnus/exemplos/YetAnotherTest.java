package br.com.cygnus.exemplos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.cygnus.exemplos.helper.InitMongoDB;
import br.com.cygnus.exemplos.persistence.model.Livro;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class YetAnotherTest {

   private MongodExecutable mongodExecutable;
   private MongodProcess mongodProcess;
   private Mongo mongo;
   private MongoTemplate mongoTemplate;

   @Before
   public void setUp() throws Exception {

      MongodStarter runtime = MongodStarter.getDefaultInstance();

      this.mongodExecutable = runtime.prepare(new MongodConfig(Version.V2_0_6, 27018, Network.localhostIsIPv6()));

      this.mongodProcess = this.mongodExecutable.start();

      this.mongo = new Mongo("localhost", 27018);

      this.mongoTemplate = new MongoTemplate(this.mongo, "exemplos");

      new InitMongoDB(this.mongoTemplate).init();
   }

   @After
   public void tearDown() throws Exception {

      this.mongodProcess.stop();

      this.mongodExecutable.stop();
   }

   @Test
   public void test() {

      List<Livro> findAll = this.mongoTemplate.findAll(Livro.class);

      assertEquals(Integer.valueOf(5), Integer.valueOf(findAll.size()));
   }
}
