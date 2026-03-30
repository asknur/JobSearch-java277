package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/response/vacancies")
@RequiredArgsConstructor
public class VacancyResponseController {
    private final VacancyService vacancyService;

    @PostMapping("/{id}/apply")
    public ResponseEntity<List<Vacancy>> respondVacancy(@PathVariable int id, @RequestParam int userId) {
        return new ResponseEntity<>(vacancyService.getRespondedVacancies(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<List<User>> getApplicants(@PathVariable int id) {
        return new ResponseEntity<>(vacancyService.getApplicantsByVacancyId(id), HttpStatus.OK);
    }
}
