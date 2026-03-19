package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.model.Vacancy;
import kg.attractor.jobsearchjava27.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyServiceImpl vacancyService;

    @PostMapping
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
        return new  ResponseEntity<>(vacancyService.save(vacancy), HttpStatus.OK);
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
        return new ResponseEntity<>(vacancyService.getVacanciesByCategory(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/apply")
    public ResponseEntity<String> respondVacancy(@PathVariable int id) {
        return new ResponseEntity<>("Отклик на вакансию " + id + " сохранён", HttpStatus.OK);
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<String> getApplicants(@PathVariable int id) {
        return new ResponseEntity<>("Список соискателей на вакансию " + id, HttpStatus.OK);
    }


}
