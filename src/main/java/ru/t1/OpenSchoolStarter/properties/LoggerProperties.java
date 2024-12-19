package ru.t1.OpenSchoolStarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "http.logger")
public class LoggerProperties {
    private boolean enabled = true;
    private LogLevel logLevel = LogLevel.valueOf("INFO"); // По умолчанию INFO

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
