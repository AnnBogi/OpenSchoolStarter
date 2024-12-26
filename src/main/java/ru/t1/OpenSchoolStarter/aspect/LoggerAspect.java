package ru.t1.OpenSchoolStarter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.t1.OpenSchoolStarter.properties.LoggerProperties;

import java.util.Arrays;

@Aspect
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    private final LoggerProperties loggerProperties;

    public LoggerAspect(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    private void log(String message) {
        String configuredLogLevel = loggerProperties.getLogLevel().toUpperCase();
        switch (configuredLogLevel) {
            case "INFO" -> logger.info(message);
            case "ERROR" -> logger.error(message);
            case "DEBUG" -> logger.debug(message);
            case "WARN" -> logger.warn(message);
            default -> {
            }
        }
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogCreateTask)")
    public void logBeforeCreateTask(JoinPoint joinPoint) {
        log("Перед созданием задачи. Метод: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogCreateTask)", returning = "result")
    public void logAfterCreateTask(JoinPoint joinPoint, Object result) {
        log("После успешного создания задачи: " + result);
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogUpdateTask)")
    public void logBeforeUpdateTask(JoinPoint joinPoint) {
        log("Перед обновлением задачи. Метод: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogUpdateTask)", throwing = "ex")
    public void logExceptionDuringUpdate(JoinPoint joinPoint, Throwable ex) {
        log("Произошла ошибка во время обновления задачи в методе: " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
        log("Стек вызовов: " + Arrays.toString(ex.getStackTrace()));
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogDeleteTask)")
    public void logBeforeDeleteTask(JoinPoint joinPoint) {
        log("Перед удалением задачи. Метод: " + joinPoint.getSignature().getName());
    }

    @After("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogDeleteTask)")
    public void logAfterDeleteTask(JoinPoint joinPoint) {
        log("После удаления задачи. Метод: " + joinPoint.getSignature().getName());
    }
}
