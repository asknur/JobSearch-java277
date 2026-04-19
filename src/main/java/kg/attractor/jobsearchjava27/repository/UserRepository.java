package kg.attractor.jobsearchjava27.repository;

import kg.attractor.jobsearchjava27.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> getByName(String name);

    Optional<User> getByPhoneNumber(String phone);

    boolean existsByEmail(String email);


}
