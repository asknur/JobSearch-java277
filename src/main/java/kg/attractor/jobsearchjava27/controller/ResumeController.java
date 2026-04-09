package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public String listResumes(Model model) {
        model.addAttribute("resume", resumeService.getAllResume());
        return "resume";
    }
}
