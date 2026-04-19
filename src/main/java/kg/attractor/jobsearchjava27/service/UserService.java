package kg.attractor.jobsearchjava27.service;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.dto.UserUpdateDto;
import kg.attractor.jobsearchjava27.exception.NotFoundEntryException;
import kg.attractor.jobsearchjava27.exception.UserDataCreateException;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    String login(UserDto user);

    User save(UserDto user);

    void update(UserUpdateDto dto, String currentEmail);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id) throws UserNotFoundException;

    UserDto getUserByName(String name) throws UserNotFoundException;

    UserDto getUserByPhone(String phone) throws UserNotFoundException;

    UserDto getUserByEmail(String email) throws UserNotFoundException;

    boolean getUserByExistEmail(String email);

    void updateAvatar(MultipartFile file, String email);
}
