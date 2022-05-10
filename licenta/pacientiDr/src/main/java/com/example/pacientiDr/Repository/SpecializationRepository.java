package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    List<Specialization> findByNumeContaining(String name);

    Specialization findByNume(String name);
}
