package com.example.CreateAccount.Registration.Service;

import com.example.CreateAccount.AppUser.Exceptions.EmailExceptionIsInvalid;
import com.example.CreateAccount.AppUser.Model.Users;
import com.example.CreateAccount.AppUser.Model.AppUserRole;
import com.example.CreateAccount.AppUser.Service.AppUserService;
import com.example.CreateAccount.Registration.Utils.EmailValidator;
import com.example.CreateAccount.Registration.Model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new EmailExceptionIsInvalid(request.getEmail());
        }

        return appUserService.singUpUser(new Users(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
    }
}
