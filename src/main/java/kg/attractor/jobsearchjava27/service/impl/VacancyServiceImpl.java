package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.VacancyDao;
import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.ResumeNotFoundException;
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
    private final List<Vacancy> vacancies;
    private final VacancyDao vacancyDao;

    @Override
    public void create(VacancyDto vac) {
        Vacancy vacancy = Vacancy.builder()
                .name(vac.getName())
                .categoryId(vac.getCategoryId())
                .createdTime(vac.getCreatedTime())
                .authorId(vac.getAuthorId())
                .description(vac.getDescription())
                .expFrom(vac.getExpFrom())
                .expTo(vac.getExpTo())
                .salary(vac.getSalary())
                .build();
        vacancyDao.createVacancy(vacancy);
    }

    public VacancyDto update(VacancyDto vac) throws VacancyNotFoundException {
        Vacancy vacancy = vacancyDao.getVacancyById(Math.toIntExact(vac.getId()))
                .orElseThrow(VacancyNotFoundException::new);
        vacancy.setName(vac.getName());
        vacancy.setCategoryId(vac.getCategoryId());
        vacancy.setCreatedTime(vac.getCreatedTime());
        vacancy.setAuthorId(vac.getAuthorId());
        vacancy.setDescription(vac.getDescription());
        vacancy.setExpFrom(vac.getExpFrom());
        vacancy.setExpTo(vac.getExpTo());
        vacancy.setSalary(vac.getSalary());
        vacancyDao.updateVacancy(vacancy);
        return vac;
    }

    @Override
    public void deleteById(int id) {
        vacancyDao.deleteVacancy(id);
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancy = vacancyDao.getAllVacancy();
        List<VacancyDto> result = new ArrayList<>();

        vacancies.forEach(e -> {
            VacancyDto vacancyDto = VacancyDto.builder()
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
    public List<Vacancy> getRespondedVacancies(int id) {
        return vacancyDao.getRespondedVacancy(id);
    }

    @Override
    public List<User> getApplicantsByVacancyId(int id) {
        return vacancyDao.getApplicantsByVacancyId(id);
    }


}
