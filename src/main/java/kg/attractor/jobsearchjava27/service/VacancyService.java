package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.VacancyDto;
import kg.attractor.jobsearchjava27.exception.VacancyNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vac, String authorEmail);

    VacancyDto update(VacancyDto vac) throws VacancyNotFoundException;

    VacancyDto findById(Long id) throws VacancyNotFoundException;

    void deleteById(Long id);

    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByCategoryId(Long id);

    List<User> getApplicantsByVacancyId(Long id);

    List<VacancyDto> getVacanciesByAuthorId(Long authorId);

    List<VacancyDto> getAllActiveVacancies();

    Page<VacancyDto> getActiveVacanciesPage(int page, int count, String sort);

    Page<VacancyDto> getVacanciesByAuthorPage(Long authorId, int page, int count);

    Page<VacancyDto> getRespondedVacanciesPage(Long applicantId, int page, int count);

    Page<VacancyDto> getRespondedVacancies(int page, int size, String sort);
}
