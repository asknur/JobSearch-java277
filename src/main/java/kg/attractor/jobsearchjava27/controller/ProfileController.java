package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.dto.UserUpdateDto;
import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.service.ImageService;
import kg.attractor.jobsearchjava27.service.ResumeService;
import kg.attractor.jobsearchjava27.service.UserService;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final ImageService imageService;

    @GetMapping
    public String profile(Model model, Principal principal,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) throws UserNotFoundException {
        UserDto user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);

        if ("APPLICANT".equals(user.getAccountType())) {
            Page<ResumeDto> resumePage = resumeService.getResumesByApplicantPage(user.getId(), page, size);
            Page<VacancyDto> vacancyPage = vacancyService.getRespondedVacanciesPage(user.getId(), page, size);
            model.addAttribute("resumes", resumePage.getContent());
            model.addAttribute("vacancies", vacancyPage.getContent());
            model.addAttribute("resumeTotalPages", resumePage.getTotalPages());
            model.addAttribute("vacancyTotalPages", vacancyPage.getTotalPages());
        } else {
            Page<VacancyDto> vacancyPage = vacancyService.getVacanciesByAuthorPage(user.getId(), page, size);
            model.addAttribute("vacancies", vacancyPage.getContent());
            model.addAttribute("vacancyTotalPages", vacancyPage.getTotalPages());
        }

        model.addAttribute("currentPage", page);
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
            model.addAttribute("errors", bindingResult);
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

    @GetMapping("/avatars/{filename}")
    @ResponseBody
    public ResponseEntity<?> getAvatar(@PathVariable String filename) {
        return imageService.getOutputFile(filename, "/avatars", MediaType.IMAGE_JPEG);
    }
}
