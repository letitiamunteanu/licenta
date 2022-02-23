package com.example.CreateAccount.AppUser.Service;

import com.example.CreateAccount.AppUser.Exceptions.UserExceptionAlreadyExists;
import com.example.CreateAccount.AppUser.Exceptions.UserExceptionNotFound;
import com.example.CreateAccount.AppUser.Model.Users;
import com.example.CreateAccount.AppUser.Repository.UserRepository;
import com.example.CreateAccount.Registration.Token.ConfirmationToken;
import com.example.CreateAccount.Registration.Token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).
                orElseThrow(() -> new UserExceptionNotFound(username));
    }

    public String singUpUser(Users appUser){
        boolean exists = userRepository.findByUsername(appUser.getUsername()).isPresent();

        if(exists){
            throw new UserExceptionAlreadyExists(appUser.getUsername());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        String token =  UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);

        //userRepository.save(appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);


        //TODO send email
        return token;
    }
}
