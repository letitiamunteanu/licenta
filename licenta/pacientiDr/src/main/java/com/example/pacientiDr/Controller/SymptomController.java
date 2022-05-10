package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Symptom;
import com.example.pacientiDr.Services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequestMapping("/api/symptoms")
@CrossOrigin("*")
public class SymptomController {

    private final SymptomService symptomService;

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @PostMapping("/addSymptom")
    public ResponseEntity<?> addNewSymptom(@RequestBody Symptom symptom, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(symptomService.addNewSymptom(symptom, jwt));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getSymptomByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(symptomService.getSymptomByName(name, jwt));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getSymptomByName(@PathVariable Integer id, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(symptomService.getSymptomById(id, jwt));
    }

    @GetMapping("/allSymptoms")
    public ResponseEntity<?> getAllSymptoms(@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(symptomService.getAllSymptoms(jwt));
    }

    @GetMapping("/allSymptomsContaining/{chars}")
    public ResponseEntity<?> getAllSymptomsContaining(@PathVariable String chars){
        return ResponseEntity.ok().body(symptomService.getAllSymptomsContaining(chars));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSymptom(@PathVariable Integer id, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt) {

        Symptom searchedSymptom = symptomService.getSymptomById(id, jwt);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Symptom.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, searchedSymptom, v);
        });

        return ResponseEntity.ok().body(symptomService.update(id, searchedSymptom, jwt));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteSymptom(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        symptomService.delete(name, jwt);
        return ResponseEntity.ok().body("");
    }
}
