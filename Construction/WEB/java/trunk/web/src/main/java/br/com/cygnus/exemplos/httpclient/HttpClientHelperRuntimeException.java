package br.com.cygnus.exemplos.httpclient;

/**
 * Encapsula todo e qualquer erro que ocorra durante o processo de conexao ou ao recuperar os dados do servico.
 * 
 * @see HttpClientHelper
 */
public class HttpClientHelperRuntimeException extends RuntimeException {

   private static final long serialVersionUID = -6938100273406106761L;

   /**
    * @param cause {@link Throwable}.
    */
   public HttpClientHelperRuntimeException(Throwable cause) {

      super(cause);
   }

}
