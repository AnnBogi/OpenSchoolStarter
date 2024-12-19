package ru.t1.OpenSchoolStarter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import ru.t1.OpenSchoolStarter.properties.LoggerProperties;




@Aspect
@Slf4j(topic = "LoggAspect")
public class LoggerAspect {
    private final LoggerProperties loggerProperties;

    public LoggerAspect(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }











    @AfterReturning(value = "@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogCreateTask)", returning = "result")
    public void logAfterCreateTask(JoinPoint joinPoint, Object result) {
        log.info("After creating a task successfully: " + result);
    }

    @Before("@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogUpdateTask)")
    public void logBeforeUpdateTask(JoinPoint joinPoint) {
        log.info("Before updating a task.  Method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(ru.t1.OpenSchoolStarter.aspect.annotation.LogException)", throwing = "ex")
    public void  logExceptionDuringUpdate(JoinPoint joinPoint, Throwable ex) {
        try {
            log.info("Exception occurred during task update in method: " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
            for (StackTraceElement element : ex.getStackTrace()) {
                log.info(element.toString());
            }
        } catch (Exception e) {
            log.info("Error in handling exception in the aspect: " + e.getMessage());
        }
    }




}