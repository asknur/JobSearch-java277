package kg.attractor.jobsearchjava27.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";

    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult,
                           Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }
        userService.save(userDto);
        try {
            request.login(userDto.getEmail(), userDto.getPassword());
        } catch (ServletException e) {
            return "redirect:/auth/login";
        }
        String accountType = userDto.getAccountType();
        if ("EMPLOYER".equalsIgnoreCase(accountType)) {
            return "redirect:/resume";
        }
        return "redirect:/";
    }


}
