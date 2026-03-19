package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/register")
    public ResponseEntity<User> getRegister(@RequestBody User user) {
        user.setAccountType(user.getAccountType());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/applicant/{id}")
    public ResponseEntity<User> getApplicant(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/employer/{id}")
    public ResponseEntity<User> getEmployer(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
