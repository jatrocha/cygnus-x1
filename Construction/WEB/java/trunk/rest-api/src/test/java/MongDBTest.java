import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.persistence.model.Carro;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;

public class MongDBTest {

   private static final String LOCALHOST = "127.0.0.1";
   private static final String DB_NAME = "itest";
   private static final int MONGO_TEST_PORT = 27027;

   private static MongodProcess mongoProcess;

   private static Mongo mongo;

   private MongoTemplate template;

   @BeforeClass
   public static void initializeDB() throws IOException {

      RuntimeConfig config = new RuntimeConfig();

      config.setExecutableNaming(new UserTempNaming());

      MongodStarter starter = MongodStarter.getInstance(config);

      MongodExecutable mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, MONGO_TEST_PORT, false));

      mongoProcess = mongoExecutable.start();

      mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);

      mongo.getDB(DB_NAME);
   }

   @AfterClass
   public static void shutdownDB() throws InterruptedException {

      mongo.close();

      mongoProcess.stop();
   }

   @Before
   public void setUp() throws Exception {

      this.template = new MongoTemplate(mongo, DB_NAME);
   }

   @After
   public void tearDown() throws Exception {

   }

   @Test
   public void test() {

      Carro carro = new Carro(Long.valueOf(1), Marca.FORD, "modelo", "versao", "motor");

      this.template.insert(carro);

      Carro actual = this.template.findById(Long.valueOf(1), Carro.class);

      assertEquals(Marca.FORD, actual.getMarca());

   }
}
