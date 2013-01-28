package br.com.cygnus.exemplos.httpclient;

/**
 * Tipos de Conteudos a serem utilizados como cabecalhos http.
 * 
 * @see HttpClientHelper
 * @see HttpResponseFactory
 * @see HttpResponseBase
 */
public enum ContentTypeEnum {

   JSON {
      /**
       * (non-Javadoc)
       * 
       * @see java.lang.Enum#toString().
       */
      @Override
      public String toString() {
         return "application/json";
      }

   }

}
