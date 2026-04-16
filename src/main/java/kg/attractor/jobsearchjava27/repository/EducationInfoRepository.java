package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.EducationInfo;
import kg.attractor.jobsearchjava27.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long> {
    List<EducationInfo> findByResume(Resume resume);

    void deleteByResume(Resume resume);
}
