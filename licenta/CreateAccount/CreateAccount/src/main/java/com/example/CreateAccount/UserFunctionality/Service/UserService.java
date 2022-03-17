package com.example.CreateAccount.UserFunctionality.Service;

import com.example.CreateAccount.UserFunctionality.Exceptions.UserExceptionAlreadyExists;
import com.example.CreateAccount.UserFunctionality.Exceptions.UserExceptionNotFound;
import com.example.CreateAccount.UserFunctionality.Exceptions.UserExceptionNotFoundByEmail;
import com.example.CreateAccount.UserFunctionality.Model.Users;
import com.example.CreateAccount.UserFunctionality.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("load user by username\n");
        Users user = userRepository.findByUsername(username);
        SimpleGrantedAuthority role = null;

        if(user != null){
            log.info("User {} found in database", username);
            role = new SimpleGrantedAuthority(user.getRole());
            System.out.println("role from user service " + user.getRole());
        }
        else{
            log.error("User {} not found in database", username);
            throw new UserExceptionNotFound(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(role));
    }

    public String singUpUser(Users appUser){
        Users exists = userRepository.findByUsername(appUser.getUsername());

        if(exists != null){
            throw new UserExceptionAlreadyExists(appUser.getUsername());
        }
        else{
            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);
            userRepository.save(appUser);

            return "Account successfully created! ";
        }
    }

    public List<Users> getAllUsers(){;
        return userRepository.findAll();
    }

    public Users getUserByUsername(String username){
        if(userRepository.existsById(username)){
            return userRepository.findByUsername(username);
        }
        else{
            throw new UserExceptionNotFound(username);
        }
    }

    public Users getUserByEmail(String email){
        if(!userRepository.findByEmail(email).equals("")){
            return userRepository.findByEmail(email);
        }
        else {
            throw new UserExceptionNotFoundByEmail(email);
        }
    }

    public List<Users> getUserByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    public List<Users> getUserByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    public Users updateUserAccount(String username, Users newUserCredentials){
        if(userRepository.existsById(username)){

            return userRepository.findById(username).map(user -> {
                user.setFirstName(newUserCredentials.getFirstName());
                user.setLastName(newUserCredentials.getLastName());
                user.setEmail(newUserCredentials.getEmail());
                user.setPassword(newUserCredentials.getPassword());
                user.setRole(newUserCredentials.getRole());
                return userRepository.save(newUserCredentials);
            }).orElseGet(() -> {

                newUserCredentials.setUsername(username);
                return userRepository.save(newUserCredentials);
            });
        }
        else{
            throw new UserExceptionNotFound(username);
        }
    }

    public String deleteUserAccount(String username){

        if(userRepository.existsById(username)){
            userRepository.deleteById(username);
            return "User account successfully deleted!";
        }
        else{
            throw new UserExceptionNotFound(username);
        }
    }
}
