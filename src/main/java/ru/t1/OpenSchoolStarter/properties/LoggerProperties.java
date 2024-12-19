package ru.t1.OpenSchoolStarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.t1.OpenSchoolStarter.aspect.LogLevel;


@Configuration
@ConfigurationProperties(prefix = "http.logger")
public class LoggerProperties {

    private boolean enabled = true;
    private String logLevel = LogLevel.INFO.name();

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
