package br.com.cygnus.exemplos;

import static com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb.MongoServerRuleBuilder.newManagedMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import javax.annotation.Resource;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.persistence.repository.LivroRepository;

import com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
// @UsingDataSet(locations = "all-logs.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class NoSQLUnitTest {

   @ClassRule
   public static ManagedMongoDb managedMongoDb = newManagedMongoDbRule().mongodPath("/opt/mongodb").appendSingleCommandLineArguments("--nojournal").build();

   @Rule
   public MongoDbRule mongoDbRule = newMongoDbRule().defaultManagedMongoDb("logs");

   @Resource
   private LivroRepository repository;

   @Resource
   private MongoTemplate mongoTemplate;

   @Test
   public void all_entries_should_be_loaded() {

      this.repository.findAll();

   }

}
