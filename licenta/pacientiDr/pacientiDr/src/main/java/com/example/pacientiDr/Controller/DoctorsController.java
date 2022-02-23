package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Doctors;
import com.example.pacientiDr.Services.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/doctors", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DoctorsController {

    private final DoctorsService doctorsService;

    @Autowired
    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @PostMapping()
    public Doctors addNewDoctor(@RequestBody Doctors doctor){
        return doctorsService.addNewDoctor(doctor);
    }

    @GetMapping("/allDoctors")
    public List<Doctors> getAllDoctors(){
        return doctorsService.getDoctors();
    }

    @GetMapping("/id/{id}")
    public Doctors getDoctorById(@PathVariable String id){
        return doctorsService.getDoctorById(id);
    }

    @GetMapping("/name/{name}")
    public List<Doctors> getDoctorsByName(@PathVariable String name){
        return doctorsService.getDoctorByName(name);
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctors> getDoctorsBySpecialization(@PathVariable String specialization){
        return doctorsService.getDoctorBySpecialization(specialization);
    }

    @PutMapping("/{id}")
    public Doctors updateDoctor(@PathVariable String id, @RequestBody Doctors doctor){
        return doctorsService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id){
        doctorsService.deleteDoctor(id);
    }
}
