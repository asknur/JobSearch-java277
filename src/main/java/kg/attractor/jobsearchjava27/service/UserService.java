package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public User save(User user) {
        return user;
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }


}
