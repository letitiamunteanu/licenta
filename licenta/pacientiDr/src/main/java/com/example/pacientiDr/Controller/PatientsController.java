package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Doctors;
import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.Services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "api/patients", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PatientsController {

    private final PatientsService patientsService;

    @Autowired
    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @PostMapping
    public Patients addNewPatient(@RequestBody Patients patient, @RequestHeader("Authorization") String jwt){
        return patientsService.addPatient(patient, jwt);
    }

    @GetMapping("/allPatients")
    public List<Patients> getAllPatients(@RequestHeader("Authorization") String jwt){
        return patientsService.getAllPatients(jwt);
    }

    @GetMapping("/id/{id}")
    public Patients getPatientById(@PathVariable String id, @RequestHeader("Authorization") String jwt){
        return patientsService.getPatientById(id, jwt);
    }

    @GetMapping("/cnp/{cnp}")
    public Patients getPatientByCnp(@PathVariable String cnp, @RequestHeader("Authorization") String jwt){
        return patientsService.getPatientByCnp(cnp, jwt);
    }

    @GetMapping("/name/{name}")
    public List<Patients> getPatientByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return patientsService.getPatientByName(name, jwt);
    }

    @PatchMapping("/{id}")
    public Patients updatePatient(@PathVariable String id, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt){

        Patients searchedPatient = patientsService.getPatientById(id,jwt);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Patients.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, searchedPatient, v);
        });

        return patientsService.update(id, searchedPatient, jwt);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id, @RequestHeader("Authorization") String jwt){
        patientsService.delete(id, jwt);
    }
}
