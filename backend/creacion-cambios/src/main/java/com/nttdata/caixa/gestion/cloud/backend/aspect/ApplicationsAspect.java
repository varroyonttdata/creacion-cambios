package com.nttdata.caixa.gestion.cloud.backend.aspect;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ApplicationsAspect {

    private static final Logger logger = LogManager.getLogger(ApplicationsAspect.class);

    @Before("execution (* com.nttdata.caixa.gestion.cloud.backend.services..*(..))")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        Object[] argsArray = joinPoint.getArgs();
        String args = Arrays.stream(argsArray)
                            .map(arg -> arg != null ? arg.toString() : "null") // Llama a toString() o muestra "null" si el argumento es nulo
                            .reduce((a, b) -> a + ", " + b) // Combina los argumentos en un solo String
                            .orElse("Sin argumentos"); // Si no hay argumentos, muestra un mensaje
        logger.info("Llamada a " + method + " con los argumentos [" +  args + "]");
    }


}
