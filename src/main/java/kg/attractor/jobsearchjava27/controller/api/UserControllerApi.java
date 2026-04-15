package kg.attractor.jobsearchjava27.controller.api;

import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.exception.UserNotFoundException;
import kg.attractor.jobsearchjava27.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerApi {
    private final UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/phone/{phone}")
    public UserDto getByPhone(@PathVariable String phone) throws UserNotFoundException {
        return userService.getUserByPhone(phone);
    }

    @GetMapping("/email/{email}")
    public UserDto getByEmail(@PathVariable String email) throws UserNotFoundException {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/name/{name}")
    public UserDto getByName(@PathVariable String name) throws UserNotFoundException {
        return userService.getUserByName(name);
    }

}
