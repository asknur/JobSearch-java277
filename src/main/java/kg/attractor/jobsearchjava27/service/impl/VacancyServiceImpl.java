package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.VacancyDao;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import kg.attractor.jobsearchjava27.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final List<Vacancy> vacancies;
    private final VacancyDao vacancyDao;

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancies.add(vacancy);
        return vacancy;
    }

    @Override
    public void deleteById(int id) {
        vacancyDao.deleteVacancy(id);
    }

    @Override
    public List<Vacancy> getAllVacancies() {
        return vacancyDao.getAllVacancy();
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
