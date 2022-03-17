package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, String> {

}
