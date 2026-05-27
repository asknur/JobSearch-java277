package kg.attractor.jobsearchjava27.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearchjava27.common.Utility;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.dto.UserUpdateDto;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.model.Role;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.repository.RoleRepository;
import kg.attractor.jobsearchjava27.repository.UserRepository;
import kg.attractor.jobsearchjava27.service.EmailService;
import kg.attractor.jobsearchjava27.service.ImageService;
import kg.attractor.jobsearchjava27.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final EmailService emailService;
    private final ImageService imageService;


    @Override
    public String login(UserDto user) {
        log.info("Logging user: {}", user);
        User foundUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return "Logged in successfully";
    }

    @Override
    public User save(UserDto userDto) {
        log.info("Saving user: {}", userDto);

        Role role = roleRepository.findByRoleName(userDto.getAccountType().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Role not found: " + userDto.getAccountType()));
        User user = new User();
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setAccountType(userDto.getAccountType());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEnabled(true);
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

    @Override
    public void update(UserUpdateDto dto, String currentEmail) {
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(user);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
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
        User user = userRepository.getByName(name)
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
    public UserDto getUserByPhone(String phone) throws UserNotFoundException {
        User user = userRepository.getByPhoneNumber(phone)
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
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public boolean getUserByExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void updateAvatar(MultipartFile file, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        String filename = imageService.saveUploadedFile(file, "/avatars");
        user.setAvatar(filename);
        userRepository.save(user);
    }

    @Override
    public Page<UserDto> getCompaniesByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByAccountType("EMPLOYER", pageable)
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .accountType(user.getAccountType())
                        .build());
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find any user with the email " + email));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void makeResetPasswdLink(HttpServletRequest request) throws UsernameNotFoundException, UnsupportedEncodingException, MessagingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteURL(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }

    @Override
    public String resetToken(String email) {
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        return token;
    }

}
