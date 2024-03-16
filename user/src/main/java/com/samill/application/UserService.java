package com.samill.application;

import java.util.List;

public interface UserService {
    Long signUp(SignUpRequest signUpRequest);
    User getUser();
    List<User> getUserList();
    User updateUser(User user);
    User deleteUser(User user);
}
