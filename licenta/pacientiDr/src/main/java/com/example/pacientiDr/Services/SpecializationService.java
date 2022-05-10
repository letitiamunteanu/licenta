package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.GlobalErrorException;
import com.example.pacientiDr.Exception.JwtException;
import com.example.pacientiDr.Model.Specialization;
import com.example.pacientiDr.Repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pacientiDr.JWT.JwtValidation.jwtValidationRequest;

@AllArgsConstructor
@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public Specialization addNewSpecialization(Specialization newSpecialization, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                if(!newSpecialization.getNume().equals("")) {
                    if (specializationRepository.findByNume(newSpecialization.getNume()) == null) {
                        specializationRepository.save(newSpecialization);
                    } else {
                        throw new GlobalErrorException("This specialization already exist!");
                    }
                }
            } else {
                throw new GlobalErrorException("You dont have the authority to do this!");
            }
        }

        return newSpecialization;
    }

    public List<Specialization> getAllSpecialization(String name){
        return specializationRepository.findByNumeContaining(name);
    }

    public void deleteSpecialization(String name, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {

                if(specializationRepository.findByNume(name) != null){
                    specializationRepository.delete(specializationRepository.findByNume(name));
                }
                else{
                    throw new GlobalErrorException("This specialization doesn't exist!");
                }

            }
            else {
                throw new GlobalErrorException("You dont have the authority to do this!");
            }
        }
    }

}
