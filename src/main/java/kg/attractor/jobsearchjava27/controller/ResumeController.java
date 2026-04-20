package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.service.CategoryService;
import kg.attractor.jobsearchjava27.service.ResumeService;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public String listResumes(Model model, Principal principal,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        Page<ResumeDto> resumePage = resumeService.getResumesForUserPage(principal.getName(), page, size);
        model.addAttribute("resumes", resumePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resumePage.getTotalPages());
        return "resume/resume";
    }

    @GetMapping("{id}")
    public String findById(Model model,  @PathVariable Long id) throws ResumeNotFoundException {
        model.addAttribute("resume", resumeService.findById(id));
        return "resume/resume-info";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "resume/resume-create";
    }

    @PostMapping("/create")
    public String createResume(@Valid @ModelAttribute ResumeDto resumeDto,
                               BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "resume/resume-create";
        }
        resumeService.create(resumeDto, principal.getName());
        return "redirect:/profile";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) throws ResumeNotFoundException {
        model.addAttribute("resumeDto", resumeService.findById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "resume/resume-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute ResumeDto resumeDto,
                       BindingResult bindingResult, @PathVariable Long id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "resume/resume-edit";
        }
        resumeDto.setId(id);
        resumeService.update(resumeDto);
        return "redirect:/profile";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        resumeService.deleteById(id);
        return "redirect:/profile";
    }
}
