package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer> {

    Symptom findByNumeSimptom(String symptomName);

    Symptom findSymptomById(Integer id);

    List<Symptom> findByNumeSimptomContaining(String chars);
}
