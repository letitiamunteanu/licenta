package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, String> {

    List<Doctors> findByNumeContaining(String name);

    List<Doctors> findByPrenumeContaining(String prenume);
}
