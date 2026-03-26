package kg.attractor.jobsearchjava27.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/response/vacancies")
@RequiredArgsConstructor
public class VacancyResponseController {

    @PostMapping("/{id}/apply")
    public ResponseEntity<String> respondVacancy(@PathVariable int id) {
        return new ResponseEntity<>("Отклик на вакансию " + id + " сохранён", HttpStatus.OK);
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<String> getApplicants(@PathVariable int id) {
        return new ResponseEntity<>("Список соискателей на вакансию " + id, HttpStatus.OK);
    }
}
