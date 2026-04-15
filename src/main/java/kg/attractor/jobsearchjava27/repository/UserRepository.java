package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
