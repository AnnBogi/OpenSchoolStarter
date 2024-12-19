package ru.t1.OpenSchoolStarter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.t1.OpenSchoolStarter.aspect.LoggerAspect;
import ru.t1.OpenSchoolStarter.properties.LoggerProperties;


@Configuration
@EnableConfigurationProperties(LoggerProperties.class)
@EnableAspectJAutoProxy
public class LoggerAutoConfiguration {

    @Bean
    public LoggerAspect loggerAspect(LoggerProperties loggerProperties) {
        return new LoggerAspect(loggerProperties);
    }
}