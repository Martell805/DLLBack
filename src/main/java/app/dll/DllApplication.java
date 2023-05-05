package app.dll;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DllApplication {

    public static void main(String[] args) {
        SpringApplication.run(DllApplication.class, args);
    }

}
