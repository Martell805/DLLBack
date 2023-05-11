package app.dll.config;

import app.dll.entity.User;
import app.dll.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateDefaultModerConfig {
    private final UserService userService;

    @PostConstruct
    public void createModer() {
        User moder;

        try {
            moder = userService.getByUsername("Moder");
        } catch (Exception e) {
            moder = userService.addModer(
                    new User(
                            0,
                            "Moder",
                            "moder",
                            "moder@moder.ru",
                            "12345",
                            "MODER"
                    )
            );
        }
    }
}
