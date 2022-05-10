package com.example.pacientiDr.Services;

import com.example.pacientiDr.Exception.*;
import com.example.pacientiDr.Model.Doctors;
import com.example.pacientiDr.Model.UsersDoctors;
import com.example.pacientiDr.Model.UsersPatients;
import com.example.pacientiDr.POJO.UsersDoctorsPOJO;
import com.example.pacientiDr.POJO.UsersPatientsPOJO;
import com.example.pacientiDr.Repository.DoctorsRepository;
import com.example.pacientiDr.Repository.UserProfileDoctorsRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.pacientiDr.JWT.JwtValidation.jwtValidationRequest;

@Service
@AllArgsConstructor
public class DoctorsService {

    private final DoctorsRepository doctorsRepository;
    private final UserProfileDoctorsRepository userProfileDoctorsRepository;

    public Doctors addNewDoctor(Doctors doctor, String jwt, String username){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            String usernameFromJwt = jwtResponse.split(" ")[0];
            if (username.equals(usernameFromJwt) || role.equals("admin")) {

                if (doctorsRepository.findById(doctor.getId()).isPresent()) {
                    throw new DoctorExceptionAlreadyExists(doctor.getId());
                } else if (doctor.getId() != null && !doctor.getId().equals("") && doctor.getNume() != null && !doctor.getNume().equals("") &&
                        doctor.getPrenume() != null && !doctor.getPrenume().equals("") && doctor.getSpecializare() != null && !doctor.getSpecializare().equals("") &&
                        doctor.getSediu() != null && !doctor.getSediu().equals("") && doctor.getSala() != null && !doctor.getSala().equals("")) {

                    doctorsRepository.save(doctor);
                    return doctor;
                } else {
                    throw new DoctorExceptionBadParams("Bad params sent! The doctor was not saved in database!");
                }
            } else {
                throw new BadAuthorizationException("You are not the admin!");
            }
        }
    }

    public List<Doctors> getDoctors(String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                return doctorsRepository.findAll();
            } else {
                throw new BadAuthorizationException("You are not the admin!");
            }
        }
    }

    public Doctors getDoctorById(String id, String jwt, String username){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            String usernameFromJwt = jwtResponse.split(" ")[0];

            UsersDoctors checkObj = userProfileDoctorsRepository.findByIdCuim(id);
            if(checkObj != null ) {
                if (checkObj.getIdUsername().equals(usernameFromJwt)
                        && checkObj.getIdUsername().equals(username)) {
                    return doctorsRepository.findById(id).orElseThrow(() -> new DoctorExceptionNotFound(id));
                } else {
                    if (role.equals("admin")) {
                        return doctorsRepository.findById(id).orElseThrow(() -> new DoctorExceptionNotFound(id));
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

    public List<Doctors> getDoctorByName(String name){

//        String jwtResponse = jwtValidationRequest(jwt);
//
//        if(jwtResponse.contains("Expired")) {
//            throw new JwtException("expired");
//        } else if (jwtResponse.contains("Invalid")) {
//            throw new JwtException("invalid");
//        } else {
//            String role = jwtResponse.split(" ")[1];
//            if (role.equals("admin")) {
                //return doctorsRepository.findAll().stream().filter(doctor -> doctor.getNume().equals(name)).collect(Collectors.toList());
                return doctorsRepository.findByNumeContaining(name);
//            } else {
//                throw new BadAuthorizationException("You are not the admin!");
//            }
//        }
    }

    public List<Doctors> getDoctorByPrenume(String prenume){

        return doctorsRepository.findByPrenumeContaining(prenume);

    }

    public List<Doctors> getDoctorByPartialName(String name){

        List<Doctors> lastNameMatching = getDoctorByName(name);
        List<Doctors> firstNameMatching = getDoctorByPrenume(name);
        return ListUtils.union(lastNameMatching, firstNameMatching).stream().distinct().collect(Collectors.toList());

    }

    public List<Doctors> getDoctorBySpecialization(String specialization, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                return doctorsRepository.findAll().stream().filter(doctor -> doctor.getSpecializare().equals(specialization)).collect(Collectors.toList());
            } else {
                throw new BadAuthorizationException("You are not the admin!");
            }
        }
    }

    public Doctors updateDoctor(String id, Doctors doctorToUpdate, String jwt, String username){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {

            String usernameFromJwt = jwtResponse.split(" ")[0];
            String role = jwtResponse.split(" ")[1];
            UsersDoctors checkObj = userProfileDoctorsRepository.findByIdCuim(id);

            if(checkObj != null) {
                if( (checkObj.getIdUsername().equals(usernameFromJwt)
                        && checkObj.getIdUsername().equals(username))
                        || role.equals("admin")){
                    return doctorsRepository.findById(id).map(doctor -> {

                        doctor.setNume(doctorToUpdate.getNume());
                        doctor.setPrenume(doctorToUpdate.getPrenume());
                        doctor.setSpecializare(doctorToUpdate.getSpecializare());
                        doctor.setSediu(doctorToUpdate.getSediu());
                        doctor.setSala(doctorToUpdate.getSala());

                        return doctorsRepository.save(doctorToUpdate);
                    }).orElseGet(() -> {
                        doctorToUpdate.setId(id);
                        return doctorsRepository.save(doctorToUpdate);
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

    public void deleteDoctor(String id, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            if (role.equals("admin")) {
                if (doctorsRepository.findById(id).isPresent()) {
                    doctorsRepository.delete(doctorsRepository.getById(id));
                } else {
                    throw new DoctorExceptionNotFound(id);
                }
            } else {
                throw new BadAuthorizationException("You are not the admin!");
            }
        }
    }

    public String doctorExistsByUsername(String username, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            UsersDoctors user = userProfileDoctorsRepository.findByIdUsername(username);
            if(user != null){
                return "The user has already completed the profile!" + user.getIdCuim();
            }
            return "The user has to complete the profile!";
        }
    }

    public String getUsernameFromCuim(String cuim, String username, String jwt){
        String jwtResponse = jwtValidationRequest(jwt);

        if(jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String usernameFromJwt = jwtResponse.split(" ")[0];
            String role = jwtResponse.split(" ")[1];
            UsersDoctors getObj = userProfileDoctorsRepository.findByIdCuim(cuim);
            if(getObj != null) {
                if ((getObj.getIdUsername().equals(usernameFromJwt)
                        && getObj.getIdUsername().equals(username))
                        || role.equals("admin")) {
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

    public UsersDoctors addDrToProfile(UsersDoctorsPOJO obj, String jwt){

        UsersDoctors linkDrToProfile = new UsersDoctors();
        boolean drCondition = false;
        boolean userCondition = false;

        RestTemplate restTemplate = new RestTemplate();

        if(doctorsRepository.findById(obj.getDoctorId()).isPresent()){
            drCondition = true;
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

        if(drCondition && userCondition){
            linkDrToProfile.setIdCuim(obj.getDoctorId());
            linkDrToProfile.setIdUsername(obj.getUsername());

            try{
                userProfileDoctorsRepository.save(linkDrToProfile);
            }
            catch(Exception e){
                throw new GlobalErrorException("Can't add more profiles to the same user!");
            }
        }

        return linkDrToProfile;
    }

    public String deleteUserDoctor(String id, String username, String jwt){

        String jwtResponse = jwtValidationRequest(jwt);

        if (jwtResponse.contains("Expired")) {
            throw new JwtException("expired");
        } else if (jwtResponse.contains("Invalid")) {
            throw new JwtException("invalid");
        } else {
            String role = jwtResponse.split(" ")[1];
            String jwtUsername = jwtResponse.split(" ")[0];
            if (role.equals("admin") || jwtUsername.equals(username)) {
                UsersDoctors user = userProfileDoctorsRepository.findByIdCuim(id);
                if (user != null) {
                    String usr = user.getIdUsername();
                    userProfileDoctorsRepository.delete(user);
                    doctorsRepository.delete(doctorsRepository.getById(id));
                    return "Doctor profile successfully deleted!" +  usr;
                } else {
                    return "Could not delete the doctor's profile!";
                }
            }
            else {
                throw new BadAuthorizationException("");
            }
        }
    }

}
