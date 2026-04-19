package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vac, String authorEmail);

    VacancyDto update(VacancyDto vac) throws VacancyNotFoundException;

    VacancyDto findById(Long id) throws VacancyNotFoundException;

    void deleteById(Long id);

    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getRespondedVacancies(Long applicantId);

    List<VacancyDto> getVacanciesByCategoryId(Long id);

    List<User> getApplicantsByVacancyId(Long id);

    List<VacancyDto> getVacanciesByAuthorId(Long authorId);

    List<VacancyDto> getAllActiveVacancies();
}
