package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.VacancyDao;
import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import kg.attractor.jobsearchjava27.repository.CategoryRepository;
import kg.attractor.jobsearchjava27.repository.RespondedApplicantRepository;
import kg.attractor.jobsearchjava27.repository.UserRepository;
import kg.attractor.jobsearchjava27.repository.VacancyRepository;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RespondedApplicantRepository respondedApplicantRepository;

    @Override
    public void create(VacancyDto vac, String authorEmail) {
        Category category = categoryRepository.findById(vac.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vacancy vacancy = Vacancy.builder()
                .name(vac.getName())
                .description(vac.getDescription())
                .salary(vac.getSalary())
                .expFrom(vac.getExpFrom())
                .expTo(vac.getExpTo())
                .isActive(true)
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .category(category)
                .author(author)
                .build();
        vacancyRepository.save(vacancy);
    }

    public VacancyDto update(VacancyDto vac) throws VacancyNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(vac.getId())
                .orElseThrow(VacancyNotFoundException::new);

        Category category = categoryRepository.findById(vac.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        vacancy.setName(vac.getName());
        vacancy.setDescription(vac.getDescription());
        vacancy.setSalary(vac.getSalary());
        vacancy.setExpFrom(vac.getExpFrom());
        vacancy.setExpTo(vac.getExpTo());
        vacancy.setCategory(category);
        vacancy.setUpdateTime(LocalDateTime.now());

        vacancyRepository.save(vacancy);
        return vac;
    }

    @Override
    public VacancyDto findById(Long id) throws VacancyNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);
        return toDto(vacancy);
    }

    @Override
    public void deleteById(Long id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        return vacancyRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<VacancyDto> getVacanciesByCategoryId(Long id) {
        return vacancyRepository.findByCategoryId(id)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<VacancyDto> getRespondedVacancies(Long id) {
        return vacancyRepository.findRespondedVacanciesByApplicantId(id)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<User> getApplicantsByVacancyId(Long id) {
        return respondedApplicantRepository.findApplicantsByVacancyId(id);
    }

    @Override
    public List<VacancyDto> getVacanciesByAuthorId(Long authorId) {
        return vacancyRepository.findByAuthorId(authorId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<VacancyDto> getAllActiveVacancies() {
        return vacancyRepository.findByIsActiveTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private VacancyDto toDto(Vacancy v) {
        return VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .isActive(v.getIsActive())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .categoryId(v.getCategory().getId())
                .authorId(v.getAuthor().getId())
                .build();
    }


}
