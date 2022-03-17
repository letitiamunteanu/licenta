package com.example.CreateAccount.UserFunctionality.Repository;

import com.example.CreateAccount.UserFunctionality.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Users, String> {

    Users findByUsername(String username);

    Users findByEmail(String email);

    List<Users> findByFirstName(String firstName);

    List<Users> findByLastName(String lastName);
}
