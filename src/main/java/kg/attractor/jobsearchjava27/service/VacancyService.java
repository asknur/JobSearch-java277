package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vac);

    VacancyDto update(VacancyDto vac) throws VacancyNotFoundException;

    VacancyDto findById(Long id) throws VacancyNotFoundException;

    void deleteById(int id);

    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getRespondedVacancies(Long applicantId);

    List<Vacancy> getVacanciesByCategoryId(int id);

    List<User> getApplicantsByVacancyId(int id);

    List<VacancyDto> getVacanciesByAuthorId(Long authorId);
}
