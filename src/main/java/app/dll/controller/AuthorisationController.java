package app.dll.controller;

import app.dll.entity.User;
import app.dll.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthorisationController {
    private final UserService userService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerSubmit(@ModelAttribute User user, HttpServletRequest req) {
        userService.add(user);

        return "redirect:/login";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("login")
    public String loginSubmit(@ModelAttribute User user, HttpServletRequest req) {
        if (!userService.authorise(user, req)) {
            return "redirect:/login";
        }

        return "redirect:/index";
    }
}
