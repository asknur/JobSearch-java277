package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeServiceImpl resumeService;

    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@RequestBody @Valid ResumeDto resumeDto) {
        return new ResponseEntity<>(resumeService.save(resumeDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable int id, @RequestBody Resume resume) {
        resume.setId(id);
        return new ResponseEntity<>(resumeService.update(resume), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable int id) {
        resumeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {
        return new ResponseEntity<>(resumeService.getAllResume(), HttpStatus.OK);
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<List<Resume>> getResumesByCategory(@PathVariable int id) {
        return new ResponseEntity<>(resumeService.getResumeByCategoryId(id), HttpStatus.OK);
    }

}
