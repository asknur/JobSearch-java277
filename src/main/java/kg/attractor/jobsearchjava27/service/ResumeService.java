package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.model.Resume;

import java.util.List;

public interface ResumeService {
    ResumeDto save(ResumeDto resume);

    Resume update(Resume resume);

    void deleteById(int id);

    List<Resume> getAllResume();

    List<Resume> getResumeByCategoryId(int category);

    List<Resume> getResumeByApplicantId(int applicantId);
}
