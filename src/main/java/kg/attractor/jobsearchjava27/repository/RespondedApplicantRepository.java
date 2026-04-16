package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.RespondedApplicant;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    List<RespondedApplicant> findByResume(Resume resume);

    List<RespondedApplicant> findByVacancy(Vacancy vacancy);

    List<RespondedApplicant> findByVacancyAndConfirmationTrue(Vacancy vacancy);

    boolean existsByResumeAndVacancy(Resume resume, Vacancy vacancy);

    List<User> findApplicantsByVacancyId(Long id);
}
