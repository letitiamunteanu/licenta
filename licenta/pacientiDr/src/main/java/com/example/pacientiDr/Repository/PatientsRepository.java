package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, String> {

    Patients findByNumeContaining(String name);

    Patients findByCnpContaining(String cnp);
}
