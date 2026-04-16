package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.service.ResumeService;
import kg.attractor.jobsearchjava27.service.UserService;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(Model model, Principal p) throws UserNotFoundException {
        String email = p.getName();
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);

        if ("APPLICANT".equals(user.getAccountType())) {
            model.addAttribute("resumes", resumeService.getResumesByApplicantId(user.getId()));
            model.addAttribute("vacancies", vacancyService.getRespondedVacancies(user.getId()));
        } else if ("EMPLOYER".equals(user.getAccountType())) {
            model.addAttribute("vacancies", vacancyService.getVacanciesByAuthorId(user.getId()));
        }

        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editProfileForm(Model model, Principal principal) throws UserNotFoundException {
        String email = principal.getName();
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile/profile-edit";
    }

    @PostMapping("/edit")
    public String updateProfile(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.save(userDto);
            model.addAttribute("users", userDto);
            return "redirect:/";
        }
        model.addAttribute("usersDto", userDto);
        return "profile/profile-edit";
    }
}
