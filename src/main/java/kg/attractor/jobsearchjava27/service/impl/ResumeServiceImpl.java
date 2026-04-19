package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.ResumeDao;
import kg.attractor.jobsearchjava27.dto.ResumeDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.repository.CategoryRepository;
import kg.attractor.jobsearchjava27.repository.ResumeRepository;
import kg.attractor.jobsearchjava27.repository.UserRepository;
import kg.attractor.jobsearchjava27.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final CategoryRepository  categoryRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void create(ResumeDto res, String applicantEmail) {
        Category category = categoryRepository.findById(res.getCategoryId())
                .orElseThrow(ResumeNotFoundException::new);

        User user = userRepository.findByEmail(applicantEmail)
                .orElseThrow(UserNotFoundException::new);

        Resume resume = Resume.builder()
                .name(res.getName())
                .salary(res.getSalary())
                .isActive(res.getIsActive())
                .createDate(LocalDateTime.now())
                .updateTime(res.getUpdateTime())
                .category(category)
                .applicant(user)
                .build();
        resumeRepository.save(resume);
    }

    @Override
    public ResumeDto update(ResumeDto res) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(res.getId())
                .orElseThrow(ResumeNotFoundException::new);

        Category category = categoryRepository.findById(res.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        resume.setName(res.getName());
        resume.setSalary(res.getSalary());
        resume.setIsActive(res.getIsActive());
        resume.setCategory(category);
        resume.setUpdateTime(LocalDateTime.now());

        resumeRepository.save(resume);
        return res;
    }

    @Override
    public List<ResumeDto> getResumesForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if ("EMPLOYER".equals(user.getAccountType())) {
            return resumeRepository.findByIsActiveTrue().stream().map(this::toDto).toList();
        }
        return resumeRepository.findByApplicantId(user.getId()).stream().map(this::toDto).toList();
    }

    @Override
    public ResumeDto findById(Long id) throws ResumeNotFoundException{
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);
        return toDto(resume);
    }

    @Override
    public void deleteById(Long id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public List<ResumeDto> getAllResume() {
        return resumeRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<ResumeDto> getAllActiveResume(){
        return resumeRepository.findByIsActiveTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<ResumeDto> getResumeByCategoryId(Long id) throws ResumeNotFoundException{
        return resumeRepository.findByCategoryId(id)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Long id) {
        return resumeRepository.findByApplicantId(id)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ResumeDto toDto(Resume r) {
        return ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreateDate())
                .updateTime(r.getUpdateTime())
                .categoryId(r.getCategory().getId())
                .applicantId(r.getApplicant().getId())
                .build();
    }

}
