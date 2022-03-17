package com.example.CreateAccount.Registration.Service;

import com.example.CreateAccount.UserFunctionality.Exceptions.EmailExceptionIsInvalid;
import com.example.CreateAccount.UserFunctionality.Model.Users;
import com.example.CreateAccount.UserFunctionality.Service.UserService;
import com.example.CreateAccount.Registration.Utility.EmailValidator;
import com.example.CreateAccount.Registration.Model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new EmailExceptionIsInvalid(request.getEmail());
        }

        return appUserService.singUpUser(new Users(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        ));
    }
}

//TODO: de trimis un cod pe telefon pt confirmare
