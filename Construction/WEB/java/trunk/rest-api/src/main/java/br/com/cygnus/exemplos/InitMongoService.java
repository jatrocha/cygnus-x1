package br.com.cygnus.exemplos;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.cygnus.exemplos.persistence.model.Role;
import br.com.cygnus.exemplos.persistence.model.User;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitMongoService {

   @Autowired
   private MongoTemplate mongoTemplate;

   public void init() {
      // Drop existing collections
      this.mongoTemplate.dropCollection("role");
      this.mongoTemplate.dropCollection("user");

      // Create new records
      Role adminRole = new Role();
      adminRole.setId(UUID.randomUUID().toString());
      adminRole.setRole(1);

      Role userRole = new Role();
      userRole.setId(UUID.randomUUID().toString());
      userRole.setRole(2);

      User john = new User();
      john.setId(UUID.randomUUID().toString());
      john.setFirstName("John");
      john.setLastName("Smith");
      john.setPassword("21232f297a57a5a743894a0e4a801fc3");
      john.setRole(adminRole);
      john.setUsername("john");

      User jane = new User();
      jane.setId(UUID.randomUUID().toString());
      jane.setFirstName("Jane");
      jane.setLastName("Adams");
      jane.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
      jane.setRole(userRole);
      jane.setUsername("jane");

      // Insert to db
      this.mongoTemplate.insert(john, "user");
      this.mongoTemplate.insert(jane, "user");
      this.mongoTemplate.insert(adminRole, "role");
      this.mongoTemplate.insert(userRole, "role");
   }
}
