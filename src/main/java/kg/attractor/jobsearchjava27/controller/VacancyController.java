package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.service.VacancyService;
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
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public String listVacancies(Model model) {
        model.addAttribute("vacancy", vacancyService.getAllVacancies());
        return "vacancy/vacancy";
    }

    @GetMapping("{id}")
    public String findById(Model model,  @PathVariable Long id) throws VacancyNotFoundException {
        model.addAttribute("vacancy", vacancyService.findById(id));
        return "vacancy/vacancy-info";
    }

    @GetMapping("/create")
    public String createVacancy(Model model) {
        model.addAttribute("vacancy", new VacancyDto());
        return "vacancy/vacancy-create";
    }

    @PostMapping("/create")
    public String createVacancy(VacancyDto vacancyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            vacancyService.create(vacancyDto);
            return "redirect:/";
        }
        model.addAttribute("vacancy", vacancyDto);
        return "vacancy/vacancy-create";
    }

    @GetMapping("/edit/{id}")
    public String editVacancy(Model model, @PathVariable Long id) {
        model.addAttribute("vacancies", List.of(vacancyService.findById(id)));
        return "vacancy/vacancy-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateVacancy(@Valid VacancyDto vacancyDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            vacancyService.update(vacancyDto);
            return "redirect:/";
        }
        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancy/vacancy-edit";
    }

}
