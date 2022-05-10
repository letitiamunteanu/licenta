package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.*;
import com.example.pacientiDr.Model.Symptom;
import com.example.pacientiDr.Repository.SymptomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pacientiDr.JWT.JwtValidation.jwtValidationRequest;

@Service
@RequiredArgsConstructor
public class SymptomService {

    private final SymptomRepository symptomRepository;

    public Symptom addNewSymptom(Symptom symptom, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {

        if (symptomRepository.findByNumeSimptom(symptom.getNumeSimptom()) == null) {
            symptomRepository.save(symptom);
            return symptom;
        } else {
            throw new SymptomExceptionAlreadyExists(symptom.getNumeSimptom());
        }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public Symptom getSymptomByName(String name, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                if(symptomRepository.findByNumeSimptom(name) != null)
                    return symptomRepository.findByNumeSimptom(name);
                else {
                    throw new SymptomExceptionNotFound(name);
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public Symptom getSymptomById(Integer id, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                if(symptomRepository.findById(id).isPresent())
                    return symptomRepository.findSymptomById(id);
                else {
                    throw new SymptomExceptionNotFound(" with id " + id.toString());
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }
    public List<Symptom> getAllSymptomsContaining(String chars) {
        return symptomRepository.findByNumeSimptomContaining(chars);
    }


    public List<Symptom> getAllSymptoms(String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return symptomRepository.findAll();
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public Symptom update(Integer id, Symptom symptom, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                return symptomRepository.findById(id).map(s -> {

                    s.setNumeSimptom(symptom.getNumeSimptom());
                    return symptomRepository.save(symptom);
                }).orElseGet(() -> {
                    symptom.setId(id);
                    return symptomRepository.save(symptom);
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
                if (symptomRepository.findByNumeSimptom(name) != null) {
                    symptomRepository.delete(symptomRepository.findByNumeSimptom(name));
                } else {
                    throw new SymptomExceptionNotFound(name);
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }
}
