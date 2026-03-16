package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class UserServiceImpl extends UserService {

    public UserServiceImpl(List<User> users) {
        super(users);
    }

}
