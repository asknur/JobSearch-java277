package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return new ResponseEntity<>(resumeService.save(resume), HttpStatus.OK);
    }
}
