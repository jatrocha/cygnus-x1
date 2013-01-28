package br.com.cygnus.exemplos.commons.exception;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.exemplos.commons.dto.ErrorDTO;

public class EngineRuntimeException extends RuntimeException {

   private static final long serialVersionUID = -6631607526253408364L;

   private List<ErrorDTO> errors = new ArrayList<ErrorDTO>();

   /**
    * Default construtor.
    */
   public EngineRuntimeException() {

      super();
   }

   public EngineRuntimeException(String arg0) {

      super(arg0);

      this.errors.add(new ErrorDTO(arg0));
   }

   public EngineRuntimeException(Throwable arg0) {

      super(arg0);
   }

   public EngineRuntimeException(String arg0, Throwable arg1) {

      super(arg0, arg1);
   }

   public List<ErrorDTO> getErrors() {

      return this.errors;
   }

   public EngineRuntimeException(List<ErrorDTO> errors) {

      this.errors = errors;
   }

}
