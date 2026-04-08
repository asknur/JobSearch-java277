package kg.attractor.jobsearchjava27.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserDataCreateException;
import kg.attractor.jobsearchjava27.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllerApi {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public void register(@Valid UserDto userDto) throws UserDataCreateException {
        userService.create(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDto user) {
        String log = userService.login(user);
        return ResponseEntity.ok(log);
    }
}
