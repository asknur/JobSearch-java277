package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.model.Vacancy;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        return "auth/register";
    }

    @RestController
    @RequestMapping("/vacancies")
    @RequiredArgsConstructor
    public static class VacancyController {
        private final VacancyService vacancyService;

        @PostMapping
        public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
            return new ResponseEntity<>(vacancyService.save(vacancy), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Vacancy> updateVacancy(@PathVariable int id, @RequestBody Vacancy vacancy) {
            vacancy.setId(id);
            return new ResponseEntity<>(vacancyService.save(vacancy), HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Vacancy> deleteVacancy(@PathVariable int id) {
            vacancyService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping
        public ResponseEntity<List<Vacancy>> getAllVacancies() {
            return new ResponseEntity<>(vacancyService.getAllVacancies(), HttpStatus.OK);
        }

        @GetMapping("/category/{id}")
        public ResponseEntity<List<Vacancy>> getVacanciesByCategory(@PathVariable int id) {
            return new ResponseEntity<>(vacancyService.getVacanciesByCategoryId(id), HttpStatus.OK);
        }
    }
}
