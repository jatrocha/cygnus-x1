package br.com.cygnus.exemplos.commons.exception;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.exemplos.commons.dto.ErrorDTO;

/**
 * Define os erros de Runtime do motor.
 */
public class EngineRuntimeException extends RuntimeException {

   private static final long serialVersionUID = -6631607526253408364L;

   private List<ErrorDTO> errors = new ArrayList<ErrorDTO>();

   /**
    * Default construtor.
    */
   public EngineRuntimeException() {

      super();
   }

   /**
    * @param arg0 {@link String}.
    */
   public EngineRuntimeException(String arg0) {

      super(arg0);

      this.errors.add(new ErrorDTO(arg0));
   }

   /**
    * @param arg0 {@link Throwable}.
    */
   public EngineRuntimeException(Throwable arg0) {

      super(arg0);
   }

   /**
    * @param arg0 {@link String}.
    * @param arg1 {@link Throwable}.
    */
   public EngineRuntimeException(String arg0, Throwable arg1) {

      super(arg0, arg1);
   }

   /**
    * @param errors {@link java.util.List}.
    */
   public EngineRuntimeException(List<ErrorDTO> errors) {

      this.errors = errors;
   }

   /**
    * @return {@link java.util.List}.
    */
   public List<ErrorDTO> getErrors() {

      return this.errors;
   }
}
