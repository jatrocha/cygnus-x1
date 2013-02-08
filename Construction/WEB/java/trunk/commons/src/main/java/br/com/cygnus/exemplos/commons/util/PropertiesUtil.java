package br.com.cygnus.exemplos.commons.util;

import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Responsavel por ler valores indexados por chaves em arquivo texto.
 */
public final class PropertiesUtil {

   private static final Logger LOG = Logger.getAnonymousLogger();

   private static PropertiesUtil instance = new PropertiesUtil();

   private PropertiesConfiguration config;

   private PropertiesUtil() {

      try {

         this.config = new PropertiesConfiguration("application.properties");

      } catch (ConfigurationException e) {

         LOG.throwing(PropertiesUtil.class.toString(), null, e);
      }
   }

   /**
    * @return {@link PropertiesUtil} singleton.
    */
   public static PropertiesUtil getInstance() {

      return instance;
   }

   /**
    * @param key {@link String} chave para identificar o valor desejado.
    * @param parameters {@link Object} varargs para substituicao dos <code>coringas</code> por valores.
    * @return {@link String} contendo o valor desejado.
    */
   public String getString(String key, Object... parameters) {

      return replaceParameters(this.config.getString(key), parameters);

   }

   /**
    * @param string {@link origem}
    * @param parameters {@link Object} varargs para substituicao dos <code>coringas</code> por valores.
    * @return {@link String} contendo o valor desejado.
    */
   protected final static String replaceParameters(final String string, final Object... parameters) {

      if (string == null) {

         return null;
      }

      if (parameters == null) {

         return string;
      }

      int counter = 1;

      String message = string;

      for (Object parameter : parameters) {

         String expression = "\\{" + counter++ + "\\}";

         message = message.replaceAll(expression, parameter == null ? null : parameter.toString().replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$"));
      }

      return message;

   }
}
