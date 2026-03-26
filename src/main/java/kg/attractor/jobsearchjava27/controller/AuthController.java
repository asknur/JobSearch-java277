package kg.attractor.jobsearchjava27.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserDataCreateException;
import kg.attractor.jobsearchjava27.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public void register(@Valid UserDto userDto) throws UserDataCreateException {
        userService.create(userDto);
    }

    @PostMapping("/login")
    public void login(@Valid UserDto userDto) throws UserDataCreateException {

    }
}
