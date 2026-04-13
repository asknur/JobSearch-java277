package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public String listResumes(Model model) {
        model.addAttribute("resume", resumeService.getAllResume());
        return "resume/resume";
    }

    @GetMapping("{id}")
    public String findById(Model model,  @PathVariable Long id) throws ResumeNotFoundException {
        model.addAttribute("resume", resumeService.findById(id));
        return "resume/resume-info";
    }

    @GetMapping("/create")
    public String createResume(Model model) {
        model.addAttribute("resume", new ResumeDto());
        return "resume/resume-create";
    }

    @PostMapping("/create")
    public String createResume(ResumeDto resumeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            resumeService.create(resumeDto);
            return "redirect:/";
        }
        model.addAttribute("resume", resumeDto);
        return "resume/resume-create";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        model.addAttribute("resumes", List.of(resumeService.findById(id)));
        return "resume/resume-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            resumeService.update(resumeDto);
            return "redirect:/";
        }
        model.addAttribute("resumeDto",  resumeDto);
        return "resume/resume-edit";
    }
}
