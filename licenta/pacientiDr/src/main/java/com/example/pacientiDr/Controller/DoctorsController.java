package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Doctors;
import com.example.pacientiDr.Services.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/doctors", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DoctorsController {

    private final DoctorsService doctorsService;

    @Autowired
    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @PostMapping()
    public Doctors addNewDoctor(@RequestBody Doctors doctor, @RequestHeader("Authorization") String jwt){
        return doctorsService.addNewDoctor(doctor, jwt);
    }

    @GetMapping("/allDoctors")
    public List<Doctors> getAllDoctors(@RequestHeader("Authorization") String jwt){
        return doctorsService.getDoctors(jwt);
    }

    @GetMapping("/id/{id}")
    public Doctors getDoctorById(@PathVariable String id, @RequestHeader("Authorization") String jwt){
        return doctorsService.getDoctorById(id, jwt);
    }

    @GetMapping("/name/{name}")
    public List<Doctors> getDoctorsByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return doctorsService.getDoctorByName(name, jwt);
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctors> getDoctorsBySpecialization(@PathVariable String specialization, @RequestHeader("Authorization") String jwt){
        return doctorsService.getDoctorBySpecialization(specialization, jwt);
    }

    @PatchMapping("/{id}")
    public Doctors updateDoctor(@PathVariable String id, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt){

        Doctors doctor = doctorsService.getDoctorById(id,jwt);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Doctors.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, doctor, v);
        });

        return doctorsService.updateDoctor(id, doctor, jwt);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable String id, @RequestHeader("Authorization") String jwt){
        doctorsService.deleteDoctor(id, jwt);
    }
}
