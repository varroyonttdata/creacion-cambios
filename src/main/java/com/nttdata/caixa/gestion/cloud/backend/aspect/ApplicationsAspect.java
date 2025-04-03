package com.nttdata.caixa.gestion.cloud.backend.aspect;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//TODO Pasarlo a @Beans en AppConfig
@Aspect
@Component
public class ApplicationsAspect {

    private static final Logger logger = LogManager.getLogger(ApplicationsAspect.class);
    //TODO Transformar los objetos a String en joinPoint.getArgs()
    @Before("execution (* com.nttdata.caixa.gestion.cloud.backend.services..*(..))")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Llamada a " + method + " con los argumentos " +  args);
    }


}
