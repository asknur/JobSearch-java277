package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public String listVacancies(Model model) {
        model.addAttribute("vacancy", vacancyService.getAllVacancies());
        return "vacancy";
    }

}
