package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.ResumeDao;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final List<Resume> resumes;
    private final List<ResumeDto> resumesDto;
    private final ResumeDao resumeDao;

    @Override
    public ResumeDto save(ResumeDto resume) {
        resumesDto.add(resume);
        return resume;
    }

    @Override
    public Resume update(Resume resume) {
        resumes.add(resume);
        return resume;
    }

    @Override
    public void deleteById(int id) {
        resumeDao.deleteResume(id);
    }

    @Override
    public List<Resume> getAllResume() {
        return resumeDao.getAllResume();
    }

    @Override
    public List<Resume> getResumeByCategoryId(int category) {
        return resumeDao.getResumeByCategoryId(category);
    }

    @Override
    public List<Resume> getResumeByApplicantId(int applicantId) {
        return resumeDao.getResumeByApplicantId(applicantId);
    }

}
