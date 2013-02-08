package br.com.cygnus.exemplos.helper;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Extends {@link CustomizableTraceInterceptor} to provide custom logging levels.
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {

   private static final long serialVersionUID = 287162721460370957L;

   private static Logger logger4J = Logger.getLogger("aop");

   /**
    * @see org.springframework.aop.interceptor.CustomizableTraceInterceptor#writeToLog(org.apache.commons.logging.Log, java.lang.String, java.lang.Throwable).
    */
   @Override
   protected void writeToLog(Log logger, String message, Throwable ex) {

      if (ex != null) {

         logger4J.debug(message, ex);

      } else {

         logger4J.debug(message);
      }

   }

   /**
    * @see org.springframework.aop.interceptor.AbstractTraceInterceptor#isInterceptorEnabled(org.aopalliance.intercept.MethodInvocation,
    *      org.apache.commons.logging.Log).
    */
   @Override
   protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {

      return true;
   }
}