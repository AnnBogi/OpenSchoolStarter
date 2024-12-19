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

    private void log(LogLevel level, String message) {
        if (loggerProperties.isEnabled()) {
            LogLevel configuredLogLevel = LogLevel.valueOf(loggerProperties.getLogLevel());
            if (level.ordinal() >= configuredLogLevel.ordinal()) {
                switch (level) {
                    case INFO -> logger.info(message);
                    case ERROR -> logger.error(message);
                    case DEBUG -> logger.debug(message);
                    case WARN -> logger.warn(message);
                }
            }
        }
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogCreateTask)")
    public void logBeforeCreateTask(JoinPoint joinPoint) {
        log(LogLevel.INFO, "Before creating a task. Method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogCreateTask)", returning = "result")
    public void logAfterCreateTask(JoinPoint joinPoint, Object result) {
        log(LogLevel.INFO, "After creating a task successfully: " + result);
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogUpdateTask)")
    public void logBeforeUpdateTask(JoinPoint joinPoint) {
        log(LogLevel.INFO, "Before updating a task. Method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogUpdateTask)", throwing = "ex")
    public void logExceptionDuringUpdate(JoinPoint joinPoint, Throwable ex) {
        log(LogLevel.ERROR, "Exception occurred during task update in method: " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
        log(LogLevel.ERROR, "Stack trace: " + Arrays.toString(ex.getStackTrace()));
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogDeleteTask)")
    public void logBeforeDeleteTask(JoinPoint joinPoint) {
        log(LogLevel.INFO, "Before deleting a task. Method: " + joinPoint.getSignature().getName());
    }

    @After("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogDeleteTask)")
    public void logAfterDeleteTask(JoinPoint joinPoint) {
        log(LogLevel.INFO, "After deleting a task. Method: " + joinPoint.getSignature().getName());
    }
}
