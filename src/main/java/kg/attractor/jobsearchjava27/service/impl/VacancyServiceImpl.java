package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.VacancyDao;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl {
    private final List<Vacancy> vacancies;
    private final VacancyDao vacancyDao;

    public Vacancy save(Vacancy vacancy) {
        vacancies.add(vacancy);
        return vacancy;
    }

    public void deleteById(int id) {
        vacancies.removeIf(vacancy -> vacancy.getId() == id);
    }

    public List<Vacancy> getAllVacancies() {
        return vacancies;
    }

    public List<Vacancy> getVacanciesByCategory(int id) {
        return vacancies.stream()
                .filter(vacancy -> vacancy.getCategoryId().equals(id))
                .toList();
    }

    public List<Vacancy> getRespondedVacancies(int id) {
        return vacancyDao.getRespondedVacancy(id);
    }

    public List<Vacancy> getAllVacancy() {
        return vacancyDao.getAllVacancy();
    }

    public List<Vacancy> getVacanciesByCategoryId (int id) {
        return vacancyDao.getVacanciesByCategoryId(id);
    }

    public List<User> getApplicantsByVacancyId(int id) {
        return vacancyDao.getApplicantsByVacancyId(id);
    }








}
