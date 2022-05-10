package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Integer> {

    List<Patients> findByNumeContaining(String name);

    Patients findByCnpContaining(String cnp);

    List<Patients> findByPrenumeContaining(String prenume);
}
