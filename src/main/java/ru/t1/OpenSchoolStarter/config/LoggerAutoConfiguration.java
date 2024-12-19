package ru.t1.OpenSchoolStarter.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1.OpenSchoolStarter.aspect.LoggerAspect;
import ru.t1.OpenSchoolStarter.properties.LoggerProperties;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerAutoConfiguration {

    @Bean
    LoggerAspect loggerAspect(LoggerProperties loggerProperties) {
        return new LoggerAspect(loggerProperties);
    }
}