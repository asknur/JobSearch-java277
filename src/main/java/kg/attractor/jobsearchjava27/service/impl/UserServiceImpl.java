package kg.attractor.jobsearchjava27.service.impl;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dao.UserDao;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserDataCreateException;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final List<User> users;
    private final UserDao userDao;

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> result = new ArrayList<>();

        users.forEach(e -> {
            UserDto user = UserDto.builder()
                    .email(e.getEmail())
                    .name(e.getName())
                    .password(e.getPassword())
                    .build();
            result.add(user);
        });

        return result;
    }

    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

    @Override
    public List<User> getUserByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public List<User> getUserByPhone(String phone) {
        return userDao.getByPhone(phone);
    }

    @Override
    public List<User> getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public boolean getUserByExistEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public void create(UserDto userDto) throws UserDataCreateException {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        userDao.create(user);
    }
}
