package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.UsersPatients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersProfilePatientsRepository extends JpaRepository<UsersPatients, Integer> {

    UsersPatients findByIdUsername(String username);

    UsersPatients findByIdPacient(Integer id);
}
