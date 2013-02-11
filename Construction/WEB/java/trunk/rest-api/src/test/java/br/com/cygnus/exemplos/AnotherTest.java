package br.com.cygnus.exemplos;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class AnotherTest {

   private MongodExecutable _mongodExe;
   private MongodProcess _mongod;
   private Mongo _mongo;

   @Before
   public void setUp() throws Exception {

      MongodStarter runtime = MongodStarter.getDefaultInstance();
      this._mongodExe = runtime.prepare(new MongodConfig(Version.Main.V2_0, 12345, Network.localhostIsIPv6()));
      this._mongod = this._mongodExe.start();

      this._mongo = new Mongo("localhost", 12345);
   }

   @After
   public void tearDown() throws Exception {

      this._mongod.stop();
      this._mongodExe.stop();
   }

   @Test
   public void test() {

      assertTrue(Boolean.TRUE);
   }
}
