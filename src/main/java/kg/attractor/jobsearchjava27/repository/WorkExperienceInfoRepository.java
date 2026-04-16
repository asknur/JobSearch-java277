package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.Resume;
import kg.attractor.jobsearchjava27.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Long> {
    List<WorkExperienceInfo> findByResume(Resume resume);
    void deleteByResume(Resume resume);
}
