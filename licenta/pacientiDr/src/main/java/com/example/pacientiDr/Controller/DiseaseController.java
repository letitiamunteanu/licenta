package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Disease;
import com.example.pacientiDr.POJO.SymptomToDiseasePOJO;
import com.example.pacientiDr.Services.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disease")
@CrossOrigin("*")
public class DiseaseController {

    private final DiseaseService diseaseService;

    @PostMapping("/addNewDisease")
    public ResponseEntity<?> addNewDisease(@RequestBody Disease disease, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(diseaseService.addNewDisease(disease, jwt));
    }

    @PostMapping("/addSymptomToDisease")
    public ResponseEntity<?> addSymptomToDisease(@RequestBody SymptomToDiseasePOJO symptomToDisease){
        return ResponseEntity.ok().body(diseaseService.symptomToDisease(symptomToDisease));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getDiseaseByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(diseaseService.getDiseaseByName(name, jwt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiseaseByName(@PathVariable Integer id, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(diseaseService.getDiseaseById(id, jwt));
    }

    @GetMapping("/allDiseases")
    public ResponseEntity<?> getAllDiseases(@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok().body(diseaseService.getAllDiseases(jwt));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<?> updateDisease(@PathVariable Integer id, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt) {

        Disease searchedDisease = diseaseService.getDiseaseById(id, jwt);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Disease.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, searchedDisease, v);
        });

        return ResponseEntity.ok().body(diseaseService.update(id, searchedDisease, jwt));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteDisease(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        diseaseService.delete(name, jwt);
        return ResponseEntity.ok().body("");
    }
}
