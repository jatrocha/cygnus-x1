package br.com.cygnus.exemplos.httpclient;

/**
 * Codigo dos status http das requisicoes.
 */
public enum StatusEnum {

   OK(Integer.valueOf(200), Boolean.FALSE),

   NOT_FOUND(Integer.valueOf(404), Boolean.TRUE);

   private final Integer code;

   private final Boolean error;

   private StatusEnum(Integer code, Boolean error) {

      this.code = code;

      this.error = error;
   }

   /**
    * @return {@link Integer} o "StatusCode".
    */
   public final Integer getCode() {

      return this.code;
   }

   /**
    * @param id {@link Integer} codigo do status http.
    * @return {@link StatusEnum} encontrado.
    */
   public static StatusEnum getBy(int id) {

      for (StatusEnum e : values()) {

         if (e.getCode().equals(Integer.valueOf(id))) {

            return e;
         }
      }

      return null;
   }

   public Boolean isAnError() {

      return this.error;
   }

}
