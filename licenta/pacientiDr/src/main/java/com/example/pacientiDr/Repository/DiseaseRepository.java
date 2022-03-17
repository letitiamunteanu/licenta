package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    Disease findByNumeBoala(String deseaseName);

    Disease findSymptomById(Integer id);
}
