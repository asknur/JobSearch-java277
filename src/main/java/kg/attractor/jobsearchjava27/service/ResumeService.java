package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import org.springframework.data.domain.Page;

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

    Page<ResumeDto> getActiveResumesPage(int page, int count);

    Page<ResumeDto> getResumesByApplicantPage(Long applicantId, int page, int count);

    Page<ResumeDto> getResumesForUserPage(String email, int page, int size);
}
