package br.com.cygnus.exemplos.commons.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cygnus.exemplos.commons.dto.ErrorDTO;

/**
 * EngineExceptionMapper.
 */
@Provider
public class EngineExceptionMapper implements ExceptionMapper<Exception> {

   private static final int HTTP_ERROR_500 = 500;

   private static final Logger LOG = LoggerFactory.getLogger(EngineExceptionMapper.class);

   /**
    * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable).
    */
   @Override
   public Response toResponse(Exception e) {

      GenericEntity<List<ErrorDTO>> entity = new ErrorDTOGT(getErrors(e));

      return Response.status(HTTP_ERROR_500).type(MediaType.APPLICATION_JSON).entity(entity).build();

   }

   private static List<ErrorDTO> getErrors(Exception e) {

      LOG.error(e.getMessage(), e);

      if (!(e instanceof EngineRuntimeException)) {

         return getErrorDTOAsList(e);

      }

      return ((EngineRuntimeException) e).getErrors().isEmpty() ? getErrorDTOAsList(e) : ((EngineRuntimeException) e).getErrors();

   }

   private static List<ErrorDTO> getErrorDTOAsList(Exception e) {

      ArrayList<ErrorDTO> errors = new ArrayList<ErrorDTO>();

      errors.add(new ErrorDTO(e.getMessage()));

      return errors;

   }

   private static class ErrorDTOGT extends GenericEntity<List<ErrorDTO>> {

      /**
       * @param entity
       */
      public ErrorDTOGT(List<ErrorDTO> entity) {

         super(entity);
      }
   }
}
