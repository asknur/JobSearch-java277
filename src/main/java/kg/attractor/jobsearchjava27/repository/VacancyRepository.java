package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByAuthor(User author);

    List<Vacancy> findByAuthorId(Long id);

    List<Vacancy> findByCategoryId(Long id);

    List<Vacancy> findByIsActiveTrue();

    List<Vacancy> findByAuthorIdAndIsActiveTrue(Long id);

    @Query("SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancy.id = :vacancyId")
    int countRespondedByVacancyId(@Param("vacancyId") Long vacancyId);

    @Query("SELECT ra.vacancy FROM RespondedApplicant ra WHERE ra.resume.applicant.id = :applicantId")
    List<Vacancy> findRespondedVacanciesByApplicantId(@Param("applicantId") Long applicantId);

    @Query("SELECT v from Vacancy v WHERE v.isActive = true " +
            "ORDER BY (SELECT COUNT(r) from RespondedApplicant r WHERE r.vacancy = v) DESC")
    Page<Vacancy> findActiveOrderByRespondedCount(Pageable pageable);



    //pageable

    Page<Vacancy> findByIsActiveTrue(Pageable pageable);

    Page<Vacancy> findByAuthorId(Long authorId, Pageable pageable);

    @Query("SELECT ra.vacancy FROM RespondedApplicant ra WHERE ra.resume.applicant.id = :applicantId")
    Page<Vacancy> findRespondedVacanciesByApplicantId(@Param("applicantId") Long applicantId, Pageable pageable);
}
