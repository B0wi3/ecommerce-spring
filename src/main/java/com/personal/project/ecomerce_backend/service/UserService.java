package com.personal.project.ecomerce_backend.service;

import com.personal.project.ecomerce_backend.api.model.RegistrationBody;
import com.personal.project.ecomerce_backend.exception.UserAlreadyExistsException;
import com.personal.project.ecomerce_backend.model.LocalUser;
import com.personal.project.ecomerce_backend.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private LocalUserDAO localUserDAO;

    public UserService(LocalUserDAO localUserDAO) {
        this.localUserDAO = localUserDAO;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || localUserDAO.findByUsernameIgnoreCase((registrationBody.getUsername())).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        //TODO: Encrypt passwords
        user.setPassword(registrationBody.getPassword());
        return localUserDAO.save(user);
    }
}
