import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"by.nikita.*"})
public class HMApplication {

    public static void main(String[] args) {
        SpringApplication.run(HMApplication.class, args);
    }
}
