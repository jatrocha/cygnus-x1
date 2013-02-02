package br.com.cygnus.exemplos.datastore;

import static org.junit.Assert.assertEquals;
import helper.AbstractTestMongoDB;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.cygnus.exemplos.persistence.model.Livro;

public class LivroDataStoreTest extends AbstractTestMongoDB {

   private LivroDataStore dataStore = null;

   @Before
   public void setUp() throws Exception {

      this.dataStore = new LivroDataStore(new MongoTemplate(mongo, DB_NAME));
   }

   @Test(expected = IllegalArgumentException.class)
   public void testSaveQuandoParametroInvalidoNull() {

      this.dataStore.save(null);
   }

   @Test
   public void testSave() {

      String id = UUID.randomUUID().toString();

      Livro livro = new Livro(id, "titulo", "autor", "genero");

      this.dataStore.save(livro);

      Livro actual = this.dataStore.find(Livro.class, livro.getId());

      assertEquals(id, actual.getId());
   }

   @Test(expected = IllegalArgumentException.class)
   public void testUpdateQuandoParametroInvalidoNull() {

      this.dataStore.update(null);
   }


}
