package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.Services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/patients", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PatientsController {

    private final PatientsService patientsService;

    @Autowired
    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @PostMapping
    public Patients addNewPatient(@RequestBody Patients patient){
        return patientsService.addPatient(patient);
    }

    @GetMapping("/allPatients")
    public List<Patients> getAllPatients(){
        return patientsService.getAllPatients();
    }

    @GetMapping("/id/{id}")
    public Patients getPatientById(@PathVariable String id){
        return patientsService.getPatientById(id);
    }

    @GetMapping("/cnp/{cnp}")
    public Patients getPatientByCnp(@PathVariable String cnp){
        return patientsService.getPatientByCnp(cnp);
    }

    @GetMapping("/name/{name}")
    public List<Patients> getPatientByName(@PathVariable String name){
        return patientsService.getPatientByName(name);
    }

    @PutMapping("/{id}")
    public Patients updatePatient(@PathVariable String id, @RequestBody Patients patient){
        return patientsService.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id){
        patientsService.delete(id);
    }
}
