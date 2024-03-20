package com.samill.missionaryBackend.user.management;

import com.samill.missionaryBackend.registration.RegistrationInternalAPI;
import com.samill.missionaryBackend.user.UserDTO;
import com.samill.missionaryBackend.user.UserExternalAPI;
import com.samill.missionaryBackend.user.UserInternalAPI;
import com.samill.missionaryBackend.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserManagement implements UserInternalAPI, UserExternalAPI {

    private UserService userService;
    private RegistrationInternalAPI registrationInternalAPI;

    @Override
    public UserDTO getUser() {
        return userService.getUser();
    }

    @Override
    public UserDTO register() {
        registrationInternalAPI.register();
        return null;
    }
}
