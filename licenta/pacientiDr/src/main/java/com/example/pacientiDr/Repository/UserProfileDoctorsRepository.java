package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.UsersDoctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDoctorsRepository extends JpaRepository<UsersDoctors, Integer> {

    UsersDoctors findByIdUsername(String username);

    UsersDoctors findByIdCuim(String id);
}
