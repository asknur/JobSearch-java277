package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByApplicantId(Long applicantId);

    List<Resume> findByApplicant(User applicant);

    List<Resume> findByIsActiveTrue();

    List<Resume> findByApplicantIdAndIsActiveTrue(Long applicantId);

    List<Resume> findByCategoryId(Long categoryId);

    List<Resume> findByCategory(Category category);

    List<Resume> findByApplicantAndIsActiveTrue(User applicant);


    //pageable

    Page<Resume> findByIsActiveTrue(Pageable pageable);

    Page<Resume> findByApplicantId(Long applicantId, Pageable pageable);
}
