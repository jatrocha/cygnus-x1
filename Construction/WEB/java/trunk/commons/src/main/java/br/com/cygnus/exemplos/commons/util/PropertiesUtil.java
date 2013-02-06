package br.com.cygnus.exemplos.commons.util;

import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

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

   public static PropertiesUtil getInstance() {
      return instance;
   }

   public String getString(String key, Object... parameters) {

      return replaceParameters(this.config.getString(key), parameters);

   }

   public static String replaceParameters(String string, Object... parameters) {

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
