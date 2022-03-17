package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.*;
import com.example.pacientiDr.Model.Disease;
import com.example.pacientiDr.Model.DiseaseSymptom;
import com.example.pacientiDr.Model.Symptom;
import com.example.pacientiDr.POJO.SymptomToDiseasePOJO;
import com.example.pacientiDr.Repository.DiseaseRepository;
import com.example.pacientiDr.Repository.DiseaseSymptomRepository;
import com.example.pacientiDr.Repository.SymptomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pacientiDr.JWT.JwtValidation.jwtValidationRequest;

@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final SymptomRepository symptomRepository;
    private final DiseaseSymptomRepository diseaseSymptomRepository;

    public Disease addNewDisease(Disease disease, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {

                if(diseaseRepository.findByNumeBoala(disease.getNumeBoala()) == null){
                    diseaseRepository.save(disease);
                    return disease;
                } else {
                    throw new DiseaseExceptionAlreadyExists(disease.getNumeBoala());
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }


    public DiseaseSymptom symptomToDisease(SymptomToDiseasePOJO symptomToDiseasePOJO){

        Symptom symptom = symptomRepository.findByNumeSimptom(symptomToDiseasePOJO.getSymptomName());

        if(symptom == null){
            throw new SymptomExceptionNotFound(symptomToDiseasePOJO.getSymptomName());
        }

        Disease disease = diseaseRepository.findByNumeBoala(symptomToDiseasePOJO.getDiseaseName());
        if(disease == null){
            throw new DiseaseExceptionNotFound(symptomToDiseasePOJO.getDiseaseName());
        }

        DiseaseSymptom diseaseSymptom = new DiseaseSymptom();
        diseaseSymptom.setBoli(disease);
        diseaseSymptom.setSimptome(symptom);
        diseaseSymptomRepository.save(diseaseSymptom);
        return diseaseSymptom;
    }

    public Disease getDiseaseByName(String name, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                if(diseaseRepository.findByNumeBoala(name) != null)
                    return diseaseRepository.findByNumeBoala(name);
                else {
                    throw new DiseaseExceptionNotFound(name);
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public Disease getDiseaseById(Integer id, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                if(diseaseRepository.findById(id).isPresent())
                    return diseaseRepository.findSymptomById(id);
                else {
                    throw new DiseaseExceptionNotFound(" with id " + id.toString());
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public List<Disease> getAllDiseases(String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return diseaseRepository.findAll();
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public Disease update(Integer id, Disease disease, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                return diseaseRepository.findById(id).map(d -> {
                    d.setNumeBoala(disease.getNumeBoala());
                    return diseaseRepository.save(disease);
                }).orElseGet(() -> {
                    disease.setId(id);
                    return diseaseRepository.save(disease);
                });
            } else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public void delete(String name, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                if (diseaseRepository.findByNumeBoala(name) != null) {
                    diseaseRepository.delete(diseaseRepository.findByNumeBoala(name));
                } else {
                    throw new DiseaseExceptionNotFound(name);
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

}
