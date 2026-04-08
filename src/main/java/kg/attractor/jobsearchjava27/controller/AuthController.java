package kg.attractor.jobsearchjava27.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        return "auth/register";
    }
}
