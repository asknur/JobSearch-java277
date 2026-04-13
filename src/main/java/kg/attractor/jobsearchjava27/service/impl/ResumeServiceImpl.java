package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.ResumeDao;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void create(ResumeDto res) {
        Resume resume = Resume.builder()
                .name(res.getName())
                .categoryId(res.getCategoryId())
                .applicantId(res.getApplicantId())
                .salary(res.getSalary())
                .createDate(res.getCreateDate())
                .isActive(res.isActive())
                .build();
        resumeDao.createResume(resume);
    }

    @Override
    public ResumeDto update(ResumeDto res) throws ResumeNotFoundException {
        Resume resume = resumeDao.getResumeById((long) Math.toIntExact(res.getId()))
                .orElseThrow(ResumeNotFoundException::new);
        resume.setName(res.getName());
        resume.setCategoryId(res.getCategoryId());
        resume.setApplicantId(res.getApplicantId());
        resume.setSalary(res.getSalary());
        resume.setUpdateTime(res.getUpdateTime());
        resumeDao.updateResume(resume);
        return res;
    }

    @Override
    public ResumeDto findById(Long id) throws ResumeNotFoundException{
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(ResumeNotFoundException::new);
        return ResumeDto.builder()
                .name(resume.getName())
                .applicantId(resume.getApplicantId())
                .salary(resume.getSalary())
                .name(resume.getName())
                .updateTime(resume.getUpdateTime())
                .createDate(resume.getCreateDate())
                .categoryId(resume.getCategoryId())
                .isActive(resume.isActive())
                .build();
    }

    @Override
    public void deleteById(int id) {
        resumeDao.deleteResume(id);
    }

    @Override
    public List<ResumeDto> getAllResume() {
        List<Resume> resumes = resumeDao.getAllResume();
        List<ResumeDto> result = new ArrayList<>();

        resumes.forEach(e -> {
            ResumeDto resumeDto = ResumeDto.builder()
                    .id(e.getId())
                    .applicantId(e.getApplicantId())
                    .salary(e.getSalary())
                    .name(e.getName())
                    .updateTime(e.getUpdateTime())
                    .createDate(e.getCreateDate())
                    .categoryId(e.getCategoryId())
                    .isActive(e.isActive())
                    .build();
            result.add(resumeDto);
        });
        return result;
    }

    @Override
    public ResumeDto getResumeByCategoryId(int id) throws ResumeNotFoundException{
        Resume resume = resumeDao.getResumeByCategoryId(id)
                .orElseThrow(ResumeNotFoundException::new);
        return ResumeDto.builder()
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createDate(resume.getCreateDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Long id) {
        return resumeDao.getResumesByApplicantId(id)
                .stream()
                .map(r -> ResumeDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .salary(r.getSalary())
                        .categoryId(r.getCategoryId())
                        .isActive(r.isActive())
                        .createDate(r.getCreateDate())
                        .updateTime(r.getUpdateTime())
                        .build())
                .toList();
    }

}
