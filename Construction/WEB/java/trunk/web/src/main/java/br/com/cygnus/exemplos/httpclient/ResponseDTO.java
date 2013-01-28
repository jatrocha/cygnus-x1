package br.com.cygnus.exemplos.httpclient;

public class ResponseDTO {

   private final StatusEnum status;

   private final String content;

   protected ResponseDTO(StatusEnum status, String content) {

      super();

      this.status = status;

      this.content = content;
   }

   public StatusEnum getStatus() {

      return this.status;
   }

   public String getContent() {

      return this.content;
   }

   public static ResponseDTO buildWith(final StatusEnum status, final String content) {

      return new ResponseDTO(status, content);
   }

}
