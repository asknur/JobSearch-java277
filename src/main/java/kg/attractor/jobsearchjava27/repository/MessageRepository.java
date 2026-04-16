package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.Message;
import kg.attractor.jobsearchjava27.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRespondedApplicant(RespondedApplicant respondedApplicant);

    List<Message> findByRespondedApplicantOrderByTimestampAsc(RespondedApplicant respondedApplicant);
}
