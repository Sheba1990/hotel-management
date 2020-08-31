package by.nikita;

import by.nikita.services.config.EmailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableConfigurationProperties(value = EmailProperties.class)
public class HMApplication {

    public static void main(String[] args) {
        SpringApplication.run(HMApplication.class, args);
    }
}
