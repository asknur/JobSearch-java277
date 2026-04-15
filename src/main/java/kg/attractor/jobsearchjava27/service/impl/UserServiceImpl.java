package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.UserDao;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserDataCreateException;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.repository.UserRepository;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public String login(UserDto user) {
        log.info("Logging user: {}", user);
        User foundUser = userDao.getByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return "Logged in successfully";
    }

    @Override
    public User save(UserDto userDto){
        log.info("Saving user: {}", userDto);
        User user = new User();
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAccountType(userDto.getAccountType());
        user.setPhoneNumber(String.valueOf(userDto.getPhoneNumber()));
        userDao.create(user);
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
    public UserDto getUserById(Long id) throws UserNotFoundException {
//        User user = userDao.findById((long) id)
//                .orElseThrow(UserNotFoundException::new);
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .accountType(user.getAccountType())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .surname(user.getSurname())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public UserDto getUserByName(String name) throws UserNotFoundException {
        User user = userDao.getByName(name)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .accountType(user.getAccountType())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .surname(user.getSurname())
                .build();
    }

    @Override
    public UserDto getUserByPhone(String phone) throws UserNotFoundException{
        User user = userDao.getByPhone(phone)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .accountType(user.getAccountType())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .surname(user.getSurname())
                .build();
    }

    @Override
    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        User user = userDao.getByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .accountType(user.getAccountType())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .surname(user.getSurname())
                .build();
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
