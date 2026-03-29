package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;

import java.util.List;

public interface ResumeService {
    void create(ResumeDto res);

    ResumeDto update(ResumeDto resume) throws ResumeNotFoundException;

    void deleteById(int id);

    List<ResumeDto> getAllResume();

    ResumeDto getResumeByCategoryId(int category) throws ResumeNotFoundException;

    ResumeDto getResumeByApplicantId(int id) throws ResumeNotFoundException;
}
