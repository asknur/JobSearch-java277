package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;

import java.util.List;

public interface VacancyService {
    Vacancy save(Vacancy vacancy);

    void deleteById(int id);

    List<Vacancy> getAllVacancies();

    List<Vacancy> getRespondedVacancies(int id);

    List<Vacancy> getAllVacancy();

    List<Vacancy> getVacanciesByCategoryId(int id);

    List<User> getApplicantsByVacancyId(int id);
}
