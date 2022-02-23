package com.example.CreateAccount.AppUser.Repository;

import com.example.CreateAccount.AppUser.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Repository
//@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
}
