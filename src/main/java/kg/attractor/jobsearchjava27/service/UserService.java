package kg.attractor.jobsearchjava27.service;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserDataCreateException;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<UserDto> getAllUsers();

    UserDto getUserById(int id) throws UserNotFoundException;

    List<User> getUserByName(String name);

    List<User> getUserByPhone(String phone);

    List<User> getUserByEmail(String email);

    boolean getUserByExistEmail(String email);

    void create(@Valid UserDto userDto) throws UserDataCreateException;
}
