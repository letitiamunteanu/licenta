package com.example.pacientiDr.Controller;


import com.example.pacientiDr.Model.Specialization;
import com.example.pacientiDr.Services.SpecializationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/specialization")
@AllArgsConstructor
@CrossOrigin("*")
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping()
    public ResponseEntity<?> addNewSpecialization(@RequestBody Specialization specialization, @RequestHeader("Authorization") String jwt){

        return ResponseEntity.ok().body(specializationService.addNewSpecialization(specialization,jwt));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getSpecializationByPartialName(@PathVariable String name){
        return ResponseEntity.ok().body(specializationService.getAllSpecialization(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteSpecialization(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        specializationService.deleteSpecialization(name,jwt);
        return ResponseEntity.ok().body("");
    }
}
