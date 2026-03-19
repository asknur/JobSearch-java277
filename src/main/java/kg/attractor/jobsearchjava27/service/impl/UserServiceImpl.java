package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.UserDao;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final List<User> users;
    private final UserDao userDao;


    public User save(User user) {
        users.add(user);
        return user;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> result = new ArrayList<>();
        users.forEach(e -> UserDto.builder()
                .name(e.getName())
                .password(e.getPassword())
                .build());
        return result;
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public List<User> getUserByName(String name) {
        return userDao.getByName(name);
    }


}
