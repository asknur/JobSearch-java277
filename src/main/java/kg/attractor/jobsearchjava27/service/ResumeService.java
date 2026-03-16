package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {
    private final List<Resume> resumes;

    public ResumeService(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public Resume save(Resume resume) {
        return resume;
    }

    public List<Resume> getAllResume() {
        return resumes;
    }

    public List<Resume> getResumeByCategory(String category) {
        return resumes.stream()
                .filter(resume -> resume.getCategoryId().equals(category))
                .toList();
    }

    public void deleteById(int id) {
        resumes.removeIf(resume -> resume.getId() == id);
    }

}
