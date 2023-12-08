package app.dll.controller;

import app.dll.entity.User;
import app.dll.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public User get(@RequestParam Integer id) {
        return userService.get(id);
    }

    @GetMapping("all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @PostMapping("login")
    public User login(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
