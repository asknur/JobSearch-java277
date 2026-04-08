package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vac);

    VacancyDto update(VacancyDto vac) throws VacancyNotFoundException;

    void deleteById(int id);

    List<VacancyDto> getAllVacancies();

    List<Vacancy> getRespondedVacancies(int id);

    List<Vacancy> getVacanciesByCategoryId(int id);

    List<User> getApplicantsByVacancyId(int id);
}
