package kg.attractor.jobsearchjava27.controller;


import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final UserService userService;

    @GetMapping
    public String listCompanies(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        Page<UserDto> companyPage = userService.getCompaniesByPage(page, size);
        model.addAttribute("companies", companyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", companyPage.getTotalPages());
        return "company/company";
    }

    @GetMapping("/{id}")
    public String companyDetail(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("company", userService.getUserById(id));
        return "company/company-info";
    }
}
