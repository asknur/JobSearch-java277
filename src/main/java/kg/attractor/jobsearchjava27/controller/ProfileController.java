package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.dto.UserUpdateDto;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.service.ResumeService;
import kg.attractor.jobsearchjava27.service.UserService;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        UserDto user = userService.getUserByEmail(p.getName());
        model.addAttribute("user", user);
        if ("APPLICANT".equals(user.getAccountType())) {
            model.addAttribute("resumes", resumeService.getResumesByApplicantId(user.getId()));
            model.addAttribute("vacancies", vacancyService.getRespondedVacancies(user.getId()));
        } else {
            model.addAttribute("vacancies", vacancyService.getVacanciesByAuthorId(user.getId()));
        }
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editProfileForm(Model model, Principal principal) throws UserNotFoundException {
        model.addAttribute("userDto", userService.getUserByEmail(principal.getName()));
        return "profile/profile-edit";
    }

    @PostMapping("/edit")
    public String updateProfile(@Valid @ModelAttribute UserUpdateDto userUpdateDto,
                                BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userUpdateDto);
            return "profile/profile-edit";
        }
        userService.update(userUpdateDto, principal.getName());
        return "redirect:/profile";
    }

    @PostMapping("/avatar")
    public String updateAvatar(@RequestParam MultipartFile avatar, Principal principal) throws UserNotFoundException {
        userService.updateAvatar(avatar, principal.getName());
        return "redirect:/profile";
    }
}
