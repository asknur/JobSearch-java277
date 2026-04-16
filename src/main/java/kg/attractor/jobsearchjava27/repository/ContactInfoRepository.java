package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.ContactInfo;
import kg.attractor.jobsearchjava27.model.ContactType;
import kg.attractor.jobsearchjava27.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    List<ContactInfo> findByResume(Resume resume);

    List<ContactInfo> findByType(ContactType type);

    void deleteByResume(Resume resume);
}
