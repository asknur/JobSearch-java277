package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public String profile(Model model, Principal p) throws UserNotFoundException {
        String email = p.getName();
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/edit")
    public String editProfileForm(Model model, Principal principal) throws UserNotFoundException {
        String email = principal.getName();
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String updateProfile(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.save(userDto);
            model.addAttribute("users", userDto);
            return "redirect:/";
        }
        model.addAttribute("usersDto", userDto);
        return "profile-edit";
    }
}
