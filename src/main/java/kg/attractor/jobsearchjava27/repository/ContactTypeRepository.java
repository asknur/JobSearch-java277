package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
    Optional<ContactType> findByType(String type);
}
