package br.com.cygnus.exemplos.commons.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Mapeamento dos erros para JSon.
 */
@XmlRootElement
public class ErrorDTO implements Serializable {

   private static final long serialVersionUID = -8633734997780819980L;

   private String description;

   /**
    * @param description {@link String} descrição do erro.
    */
   public ErrorDTO(String description) {

      this.description = description;
   }

   /**
    * Construtor padrao.
    */
   public ErrorDTO() {

      super();
   }

   /**
    * @return {@link String} descrição do erro.
    */
   public String getDescription() {

      return this.description;
   }

   /**
    * @param description {@link String} descrição do erro.
    */
   public void setDescription(String description) {

      this.description = description;
   }

}
