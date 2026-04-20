package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.service.CategoryService;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping
    public String listVacancies(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        Page<VacancyDto> vacancyPage = vacancyService.getActiveVacanciesPage(page, size);
        model.addAttribute("vacancy", vacancyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vacancyPage.getTotalPages());
        return "vacancy/vacancy";
    }

    @GetMapping("{id}")
    public String findById(Model model,  @PathVariable Long id) throws VacancyNotFoundException {
        model.addAttribute("vacancy", vacancyService.findById(id));
        return "vacancy/vacancy-info";
    }

    @GetMapping("/create")
    public String createVacancy(Model model) {
        model.addAttribute("vacancyDto", new VacancyDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "vacancy/vacancy-create";
    }

    @PostMapping("/create")
    public String createVacancy(@Valid @ModelAttribute VacancyDto vacancyDto, BindingResult bindingResult,
                                Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "vacancy/vacancy-create";
        }
        vacancyService.create(vacancyDto, principal.getName());
        return "redirect:/profile";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable Long id) throws VacancyNotFoundException {
        model.addAttribute("vacancyDto", vacancyService.findById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "vacancy/vacancy-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute VacancyDto vacancyDto, BindingResult errors,
                         @PathVariable Long id, Model model) throws VacancyNotFoundException {
        if (errors.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "vacancy/vacancy-edit";
        }
        vacancyDto.setId(id);
        vacancyService.update(vacancyDto);
        return "redirect:/profile";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        vacancyService.deleteById(id);
        return "redirect:/profile";
    }

}
