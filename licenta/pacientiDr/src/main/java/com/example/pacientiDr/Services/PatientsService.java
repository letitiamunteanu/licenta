package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.*;
import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.Model.UsersDoctors;
import com.example.pacientiDr.Model.UsersPatients;
import com.example.pacientiDr.POJO.UsersPatientsPOJO;
import com.example.pacientiDr.Repository.PatientsRepository;
import com.example.pacientiDr.Repository.UsersProfilePatientsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.pacientiDr.JWT.JwtValidation.jwtValidationRequest;

@Slf4j
@Service
public class PatientsService {

    private final PatientsRepository patientsRepository;
    private final UsersProfilePatientsRepository usersProfilePatientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository, UsersProfilePatientsRepository usersProfilePatients) {
        this.patientsRepository = patientsRepository;
        this.usersProfilePatientsRepository = usersProfilePatients;
    }

    public Patients addPatient(Patients patient, String jwt, String username){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {

            String usernameFromJwt = jwtResponse.split(" ")[0];
            if (username.equals(usernameFromJwt)) {
                if(patient.getNume() != null && patient.getPrenume() != null && patient.getVarsta() != null
                        && patient.getGreutate() != null && patient.getInaltime() != null && patient.getNrTelefon() != null
                        && patient.getGrupaSange() != null && patient.getCnp() != null && patient.getDataNastere() != null){

                    patientsRepository.save(patient);
                    return patient;
                } else {

                    throw new PatientExceptionBadParams("Bad params sent! The patient was not saved in database!");
                }

            } else {
                throw new GlobalErrorException("You are not the owner of this account!");
            }
        }
    }

    public Patients getPatientById(Integer id, String jwt, String username) {
        System.out.println("ook");
        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            String usernameFromJwt = jwtResponse.split(" ")[0];

            UsersPatients checkObj = usersProfilePatientsRepository.findByIdPacient(id);
            if(checkObj != null ) {

                if (checkObj.getIdUsername().equals(usernameFromJwt)
                        && checkObj.getIdUsername().equals(username)) {
                    return patientsRepository.findById(id).orElseThrow(() -> new PatientExceptionNotFound(id));
                } else {
                    System.out.println("else docrror");
                    if (role.equals("admin") || role.equals("doctor")) {
                        return patientsRepository.findById(id).orElseThrow(() -> new PatientExceptionNotFound(id));
                    } else {
                        throw new BadAuthorizationException("You don't have the permission to see this profile");
                    }
                }
            }
            else{
                throw new GlobalErrorException("This profile doesn't exist!");
            }
        }
    }

    public List<Patients> getAllPatients(String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findAll();
            }
            else {
                throw new BadAuthorizationException("You dont have the permission!");
            }
        }
    }

    public List<Patients> getPatientsByPrenume(String prenume, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findByPrenumeContaining(prenume);
            }
            else {
                throw new BadAuthorizationException("You dont have the permission!");
            }
        }
    }

    public List<Patients> getPatientsByNume(String nume, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findByNumeContaining(nume);
            }
            else {
                throw new BadAuthorizationException("You dont have the permission!");
            }
        }
    }

    public List<Patients> getPatientByPartialName(String name, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                List<Patients> lastNameMatching = getPatientsByNume(name, jwt);
                List<Patients> firstNameMatching = getPatientsByPrenume(name, jwt);
                return ListUtils.union(lastNameMatching, firstNameMatching).stream().distinct().collect(Collectors.toList());
            }
            else {
                throw new BadAuthorizationException("You dont have the permission!");
            }
        }
    }

    public Patients getPatientByCnp(String cnp, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin") || role.equals("doctor")) {
                return patientsRepository.findByCnpContaining(cnp);
            }
            else {
                throw new BadAuthorizationException("You dont have the permission!");
            }
        }
    }

    public String getUsernameFromPatientId(Integer id, String username, String jwt){
        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String usernameFromJwt = jwtResponse.split(" ")[0];
            String role = jwtResponse.split(" ")[1];
            UsersPatients getObj = usersProfilePatientsRepository.findByIdPacient(id);
            if(getObj != null) {
                if ((getObj.getIdUsername().equals(usernameFromJwt)
                        && getObj.getIdUsername().equals(username))
                        || role.equals("admin") || role.equals("doctor")) {
                    return getObj.getIdUsername();
                }
                else{
                    throw new BadAuthorizationException("");
                }
            }
            else {
                throw  new GlobalErrorException("This profile doesnt exist!");
            }
        }
    }

    public Patients update(Integer id, Patients patientToUpdate, String jwt, String username){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String usernameFromJwt = jwtResponse.split(" ")[0];
            String role = jwtResponse.split(" ")[1];
            UsersPatients checkObj = usersProfilePatientsRepository.findByIdPacient(id);
            if(checkObj != null) {
                if ((checkObj.getIdUsername().equals(usernameFromJwt)
                        && checkObj.getIdUsername().equals(username))
                        || role.equals("admin") || role.equals("doctor")) {
                    return patientsRepository.findById(id).map(p -> {

                        p.setNume(patientToUpdate.getNume());
                        p.setPrenume(patientToUpdate.getPrenume());
                        p.setCnp(patientToUpdate.getCnp());
                        p.setDataNastere(patientToUpdate.getDataNastere());
                        p.setVarsta(patientToUpdate.getVarsta());
                        p.setInaltime(patientToUpdate.getInaltime());
                        p.setGreutate(patientToUpdate.getGreutate());
                        p.setNrTelefon(patientToUpdate.getNrTelefon());
                        p.setGrupaSange(patientToUpdate.getGrupaSange());
                        p.setSimptome(patientToUpdate.getSimptome());
                        p.setSpecificatii(patientToUpdate.getSpecificatii());

                        return patientsRepository.save(patientToUpdate);
                    }).orElseGet(() -> {
                        patientToUpdate.setId(id);
                        return patientsRepository.save(patientToUpdate);
                    });
                } else {
                    throw new BadAuthorizationException("");
                }
            }
            else{
                throw  new GlobalErrorException("This profile doesnt exist!");
            }
        }
    }

    public String deletePatientByUsername(String username, String jwt) {

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            String jwtUsername = jwtResponse.split(" ")[0];
            if (role.equals("admin") || role.equals("doctor") || jwtUsername.equals(username)) {
                UsersPatients user = usersProfilePatientsRepository.findByIdUsername(username);
                if (user != null) {
                    usersProfilePatientsRepository.delete(user);
                    patientsRepository.delete(patientsRepository.getById(user.getIdPacient()));
                    return "Patient profile successfully deleted!" ;
                } else {
                    return "Could not delete the patient's profile!";
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

    public UsersPatients addUserToProfile(UsersPatientsPOJO obj, String jwt){

        UsersPatients linkUserToProfile = new UsersPatients();
        boolean patientCondition = false;
        boolean userCondition = false;

        RestTemplate restTemplate = new RestTemplate();

        if(patientsRepository.findById(obj.getPacientId()).isPresent()){
            patientCondition = true;
        }

        try{

            String url  = "http://localhost:8889/api/userController/getUserByUsername/" + obj.getUsername();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(jwt.substring(7));
            HttpEntity<String> request = new HttpEntity<>("", headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            userCondition = true;

        }
        catch (Exception ex){
            throw new GlobalErrorException(ex.getMessage());
        }

        if(patientCondition && userCondition){
            linkUserToProfile.setIdPacient(obj.getPacientId());
            linkUserToProfile.setIdUsername(obj.getUsername());

            try{
                usersProfilePatientsRepository.save(linkUserToProfile);
            }
            catch(Exception e){
                throw new GlobalErrorException("Can't add more profiles to the same user!");
            }
        }

        return linkUserToProfile;
    }

    public String patientExistsByUsername(String username, String jwt){

        UsersPatients user = usersProfilePatientsRepository.findByIdUsername(username);
        if(user != null){
            return "The user has already completed the profile!" + user.getIdPacient().toString();
        }
        return "The user has to complete the profile!";
    }

    public String deleteUserPatient(Integer id, String username, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            String jwtUsername = jwtResponse.split(" ")[0];
            if (role.equals("admin") || role.equals("doctor") || jwtUsername.equals(username)) {
                UsersPatients user = usersProfilePatientsRepository.findByIdPacient(id);
                if (user != null) {
                    String usr = user.getIdUsername();
                    Integer patientId = user.getIdPacient();
                    usersProfilePatientsRepository.delete(user);
                    patientsRepository.delete(patientsRepository.getById(patientId));
                    return "Patient profile successfully deleted!" +  usr;
                } else {
                    return "Could not delete the patient's profile!";
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }
}
