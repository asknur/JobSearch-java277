package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.VacancyDao;
import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    @Override
    public void create(VacancyDto vac) {
        Vacancy vacancy = Vacancy.builder()
                .name(vac.getName())
                .categoryId(vac.getCategoryId())
                .createdDate(vac.getCreatedDate())
                .authorId(vac.getAuthorId())
                .description(vac.getDescription())
                .expFrom(vac.getExpFrom())
                .expTo(vac.getExpTo())
                .salary(vac.getSalary())
                .build();
        vacancyDao.createVacancy(vacancy);
    }

    public VacancyDto update(VacancyDto vac) throws VacancyNotFoundException {
        Vacancy vacancy = vacancyDao.getVacancyById((vac.getId()))
                .orElseThrow(VacancyNotFoundException::new);
        vacancy.setName(vac.getName());
        vacancy.setCategoryId(vac.getCategoryId());
        vacancy.setCreatedDate(vac.getCreatedDate());
        vacancy.setAuthorId(vac.getAuthorId());
        vacancy.setDescription(vac.getDescription());
        vacancy.setExpFrom(vac.getExpFrom());
        vacancy.setExpTo(vac.getExpTo());
        vacancy.setSalary(vac.getSalary());
        vacancyDao.updateVacancy(vacancy);
        return vac;
    }

    @Override
    public VacancyDto findById(Long id) throws VacancyNotFoundException{
        Vacancy vacancy = vacancyDao.getVacancyById(id)
                .orElseThrow(VacancyNotFoundException::new);
        return VacancyDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategoryId())
                .createdDate(vacancy.getCreatedDate())
                .authorId(vacancy.getAuthorId())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .salary(vacancy.getSalary())
                .build();
    }

    @Override
    public void deleteById(int id) {
        vacancyDao.deleteVacancy(id);
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyDao.getAllVacancy();
        List<VacancyDto> result = new ArrayList<>();
        vacancies.forEach(e -> {
            VacancyDto vacancyDto = VacancyDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .expTo(e.getExpTo())
                    .expFrom(e.getExpFrom())
                    .authorId(e.getAuthorId())
                    .description(e.getDescription())
                    .salary(e.getSalary())
                    .isActive(e.isActive())
                    .updateTime(e.getUpdateTime())
                    .build();
            result.add(vacancyDto);
        });
           return result;
    }

    @Override
    public List<Vacancy> getVacanciesByCategoryId(int id) {
        return vacancyDao.getVacanciesByCategoryId(id);
    }

    @Override
    public List<VacancyDto> getRespondedVacancies(Long applicantId) {
        return vacancyDao.getRespondedVacancies(applicantId)
                .stream()
                .map(v -> VacancyDto.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .description(v.getDescription())
                        .salary(v.getSalary())
                        .isActive(v.isActive())
                        .expFrom(v.getExpFrom())
                        .expTo(v.getExpTo())
                        .build())
                .toList();
    }

    @Override
    public List<User> getApplicantsByVacancyId(int id) {
        return vacancyDao.getApplicantsByVacancyId(id);
    }

    @Override
    public List<VacancyDto> getVacanciesByAuthorId(Long authorId) {
        return vacancyDao.getVacanciesByAuthorId(authorId)
                .stream()
                .map(v -> VacancyDto.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .description(v.getDescription())
                        .salary(v.getSalary())
                        .isActive(v.isActive())
                        .expFrom(v.getExpFrom())
                        .expTo(v.getExpTo())
                        .respondedCount(vacancyDao.getRespondedCountByVacancyId(v.getId()))
                        .build())
                .toList();
    }


}
