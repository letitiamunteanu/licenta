package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.POJO.UsersPatientsPOJO;
import com.example.pacientiDr.Services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/patients", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin("*")
public class PatientsController {

    private final PatientsService patientsService;

    @Autowired
    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }


    @PostMapping("/{username}")
    public ResponseEntity<?> addNewPatient(@PathVariable String username, @RequestBody Patients patient, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.addPatient(patient, jwt, username));
    }

    @PostMapping("/link")
    public ResponseEntity<?> linkUserToProfile(@RequestBody UsersPatientsPOJO obj, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.addUserToProfile(obj,jwt));
    }

    @GetMapping("/allPatients")
    public ResponseEntity<?> getAllPatients(@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.getAllPatients(jwt));
    }

    @GetMapping("/{id}/{username}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer id, @PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.getPatientById(id, jwt, username));
    }

    @GetMapping("/cnp/{cnp}")
    public ResponseEntity<?> getPatientByCnp(@PathVariable String cnp, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.getPatientByCnp(cnp, jwt));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPatientByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.getPatientByPartialName(name, jwt));
    }

    @GetMapping("/checkUserProfile/{username}")
    public ResponseEntity<?> checkProfileForUser(@PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.patientExistsByUsername(username,jwt));
    }

    @GetMapping("/getPatientUsername/{id}/{username}")
    public ResponseEntity<?> getUsernameFromPatientIf(@PathVariable Integer id, @PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.getUsernameFromPatientId(id, username, jwt));
    }

    @PatchMapping("/{id}/{username}")
    public ResponseEntity<?> updatePatient(@PathVariable Integer id, @PathVariable String username, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt){

        Patients searchedPatient = patientsService.getPatientById(id,jwt, username);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Patients.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, searchedPatient, v);
        });

        return ResponseEntity.ok().body(patientsService.update(id, searchedPatient, jwt, username));
    }

    @DeleteMapping("/deletePatient/{username}")
    public ResponseEntity<?> deletePatient(@PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.deletePatientByUsername(username, jwt));
    }

    @DeleteMapping("/deletePatient/{id}/{username}")
    public ResponseEntity<?> deleteLinkAndProfile(@PathVariable Integer id, @PathVariable String username, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(patientsService.deleteUserPatient(id, username,jwt));
    }

}
