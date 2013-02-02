package helper;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.com.cygnus.framework.AObjetoGenerico;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;

/**
 * Responsavel por carregar uma instancia do MongoDB para testes unitarios.
 */
public abstract class AbstractTestMongoDB extends AObjetoGenerico {

   protected static final String LOCALHOST = "127.0.0.1";

   protected static final String DB_NAME = "exemplos";

   protected static final int MONGO_TEST_PORT = 27028;

   private static MongodProcess mongoProcess;

   protected static Mongo mongo;

   @BeforeClass
   public static void setup() throws IOException {

      RuntimeConfig config = new RuntimeConfig();

      config.setExecutableNaming(new UserTempNaming());

      MongodStarter starter = MongodStarter.getInstance(config);

      MongodExecutable mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, MONGO_TEST_PORT, false));

      mongoProcess = mongoExecutable.start();

      mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);

      mongo.getDB(DB_NAME);
   }

   @AfterClass
   public static void tearDown() throws InterruptedException {

      mongo.close();

      mongoProcess.stop();
   }

}
