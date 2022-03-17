package com.example.pacientiDr.Repository;

import com.example.pacientiDr.Model.DiseaseSymptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseSymptomRepository extends JpaRepository<DiseaseSymptom, Integer> {
}
