package br.com.cygnus.exemplos.business.impl;

import static br.com.cygnus.framework.IObjetoGenerico.NULL_STRING;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;
import br.com.cygnus.exemplos.persistence.model.Livro;

public abstract class LivroBusinessTestBase {

   protected final String ID = "83df89af-9824-400e-9eab-dd13408d9e30";

   protected final LivroDTO LIVRO_VAZIO = new LivroDTO();

   protected final LivroDTO LIVRO_COM_ID = LivroDTO.buildWith(this.ID);

   protected final LivroDTO LIVRO_COM_ID_VAZIO = LivroDTO.buildWith(NULL_STRING);

   protected final LivroDTO LIVRO_PARA_INCLUSAO = LivroDTO.buildWith("titulo", "autor", "genero");

   protected final LivroDTO LIVRO_PARA_ATUALIZACAO = LivroDTO.buildWith(this.ID, "titulo", "autor", "genero");

   protected final LivroFilterDTO LIVRO_FILTER_VAZIO = new LivroFilterDTO();

   protected final LivroFilterDTO LIVRO_FILTER_ID_VAZIO = LivroFilterDTO.buildWith(NULL_STRING);

   protected final LivroFilterDTO LIVRO_FILTER_COM_ID = LivroFilterDTO.buildWith(this.ID);

   protected final Livro LIVRO_PARA_LEITURA = new Livro(this.ID, "titulo", "autor", "genero");

   // private static MongodExecutable mongodExecutable;
   // private static MongodProcess mongodProcess;
   // private static Mongo mongo;
   // private static MongoTemplate mongoTemplate;
   //
   // @BeforeClass
   // public static void before() throws Exception {
   //
   // MongodStarter runtime = MongodStarter.getDefaultInstance();
   //
   // mongodExecutable = runtime.prepare(new MongodConfig(Version.V2_0_6, 27017, Network.localhostIsIPv6()));
   //
   // mongodProcess = mongodExecutable.start();
   //
   // mongo = new Mongo("localhost", 27017);
   //
   // mongoTemplate = new MongoTemplate(mongo, "exemplos");
   //
   // new InitMongoDB(mongoTemplate).init();
   // }
   //
   // @AfterClass
   // public static void destroy() throws Exception {
   //
   // mongodProcess.stop();
   //
   // mongodExecutable.stop();
   // }
}
