package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("vacancy", vacancyService.getAllActiveVacancies());
        return "index";
    }


}
