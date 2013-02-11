package br.com.cygnus.exemplos;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Configuration
@Profile("test")
public class LocalhostMongoAppConfig {

   private static final String DATABASE_NAME = "exemplos";

   public @Bean
   Mongo mongo() throws UnknownHostException, MongoException {
      Mongo mongo = new Mongo("localhost");
      return mongo;
   }

   public @Bean
   MongoTemplate mongoTemplate() throws UnknownHostException, MongoException {
      MongoTemplate mongoTemplate = new MongoTemplate(this.mongo(), DATABASE_NAME);
      return mongoTemplate;
   }

}
