package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Doctors;
import com.example.pacientiDr.POJO.UsersDoctorsPOJO;
import com.example.pacientiDr.POJO.UsersPatientsPOJO;
import com.example.pacientiDr.Services.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/doctors", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("*")
public class DoctorsController {

    private final DoctorsService doctorsService;

    @Autowired
    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<?> addNewDoctor(@PathVariable String username, @RequestBody Doctors doctor, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.addNewDoctor(doctor, jwt, username));
    }

    @PostMapping("/link")
    public ResponseEntity<?> linkDoctorToProfile(@RequestBody UsersDoctorsPOJO obj, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.addDrToProfile(obj,jwt));
    }

    @GetMapping("/allDoctors")
    public ResponseEntity<?> getAllDoctors(@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.getDoctors(jwt));
    }

    @GetMapping("/id/{id}/{username}")
    public ResponseEntity<?> getDoctorById(@PathVariable String id, @PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.getDoctorById(id, jwt, username));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getDoctorsByPartialName(@PathVariable String name){
        return ResponseEntity.ok().body(doctorsService.getDoctorByPartialName(name));
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<?> getDoctorsBySpecialization(@PathVariable String specialization, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.getDoctorBySpecialization(specialization, jwt));
    }

    @GetMapping("/checkDrProfile/{username}")
    public ResponseEntity<?> checkProfileForDoctor(@PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.doctorExistsByUsername(username,jwt));
    }

    @GetMapping("/getUsername/{cuim}/{username}")
    public ResponseEntity<?> getUsernameFromCuim(@PathVariable String username, @PathVariable String cuim, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.getUsernameFromCuim(cuim, username, jwt));
    }


    @PatchMapping("/update/{id}/{username}")
    public ResponseEntity<?> updateDoctor(@PathVariable String id, @PathVariable String username, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt){

        Doctors doctor = doctorsService.getDoctorById(id,jwt,username);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Doctors.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, doctor, v);
        });

        return ResponseEntity.ok().body(doctorsService.updateDoctor(id, doctor, jwt, username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable String id, @RequestHeader("Authorization") String jwt){
        doctorsService.deleteDoctor(id, jwt);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/delete/{id}/{username}")
    public ResponseEntity<?> deleteLinkAndProfile(@PathVariable String id, @PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(doctorsService.deleteUserDoctor(id, username,jwt));
    }
}
