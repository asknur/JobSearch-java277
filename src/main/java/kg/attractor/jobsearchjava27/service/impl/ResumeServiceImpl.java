package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl {
    private final List<Resume> resumes;

    public Resume save(Resume resume) {
        resumes.add(resume);
        return resume;
    }

    public void deleteById(int id) {
        resumes.removeIf(resume -> resume.getId() == id);
    }

    public List<Resume> getAllResume() {
        return resumes;
    }

    public List<Resume> getResumeByCategory(int category) {
        return resumes.stream()
                .filter(resume -> resume.getCategoryId().equals(category))
                .toList();
    }
}
