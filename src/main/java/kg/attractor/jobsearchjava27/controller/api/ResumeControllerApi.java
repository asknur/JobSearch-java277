package kg.attractor.jobsearchjava27.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeControllerApi {
    private final ResumeService resumeService;

    @PostMapping
    public void createResume(@RequestBody @Valid ResumeDto resumeDto) {
        resumeService.create(resumeDto);
    }

    @PutMapping("/update/{id}")
    public ResumeDto updateResume(@PathVariable Long id, @RequestBody ResumeDto resume) throws ResumeNotFoundException {
        resume.setId(id);
        return resumeService.update(resume);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable int id) {
        resumeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return new ResponseEntity<>(resumeService.getAllResume(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResumeDto getResumesByCategoryId(@PathVariable int id) throws ResumeNotFoundException {
        return resumeService.getResumeByCategoryId(id);
    }

    @GetMapping("/applicant/{id}")
    public ResumeDto getResumesByApplicantId(@PathVariable int id) throws ResumeNotFoundException {
        return resumeService.getResumeByApplicantId(id);
    }



}
