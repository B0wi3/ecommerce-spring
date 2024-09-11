package com.personal.project.ecomerce_backend.service;

import com.personal.project.ecomerce_backend.api.model.LoginBody;
import com.personal.project.ecomerce_backend.api.model.RegistrationBody;
import com.personal.project.ecomerce_backend.exception.EmailFailureException;
import com.personal.project.ecomerce_backend.exception.UserAlreadyExistsException;
import com.personal.project.ecomerce_backend.model.LocalUser;
import com.personal.project.ecomerce_backend.model.VerificationToken;
import com.personal.project.ecomerce_backend.model.dao.LocalUserDAO;
import com.personal.project.ecomerce_backend.model.dao.VerificationTokenDAO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {

    private LocalUserDAO localUserDAO;
    private VerificationTokenDAO verificationTokenDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    private EmailService emailService;

    public UserService(LocalUserDAO localUserDAO, VerificationTokenDAO verificationTokenDAO, EncryptionService encryptionService, JWTService jwtService, EmailService emailService) {
        this.localUserDAO = localUserDAO;
        this.verificationTokenDAO = verificationTokenDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {
        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || localUserDAO.findByUsernameIgnoreCase((registrationBody.getUsername())).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        verificationTokenDAO.save(verificationToken);
        return localUserDAO.save(user);
    }

    private VerificationToken createVerificationToken (LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis());
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
