package br.com.cygnus.exemplos.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {

   @Id
   private String id;

   private Integer role;

   public Role(String id, Integer role) {

      this();

      this.id = id;

      this.role = role;
   }

   public Role() {

      super();
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Integer getRole() {
      return this.role;
   }

   public void setRole(Integer role) {
      this.role = role;
   }
}
