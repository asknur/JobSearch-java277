package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;

import java.util.List;

public interface ResumeService {

    void create(ResumeDto res, String authorEmail);

    ResumeDto update(ResumeDto resume) throws ResumeNotFoundException;

    List<ResumeDto> getResumesForUser(String email);

    ResumeDto findById(Long id) throws ResumeNotFoundException;

    void deleteById(Long id);

    List<ResumeDto> getAllResume();

    List<ResumeDto> getAllActiveResume();

    List<ResumeDto> getResumeByCategoryId(Long category) throws ResumeNotFoundException;

    List<ResumeDto> getResumesByApplicantId(Long id) throws ResumeNotFoundException;
}
