package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Patients;
import com.example.pacientiDr.Model.Symptom;
import com.example.pacientiDr.Services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/symptoms")
public class SymptomController {

    private final SymptomService symptomService;

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @PostMapping("/addSymptom")
    public Symptom addNewSymptom(@RequestBody Symptom symptom, @RequestHeader("Authorization") String jwt){
        return symptomService.addNewSymptom(symptom, jwt);
    }

    @GetMapping("/name/{name}")
    public Symptom getSymptomByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return symptomService.getSymptomByName(name, jwt);
    }

    @GetMapping("/id/{id}")
    public Symptom getSymptomByName(@PathVariable Integer id, @RequestHeader("Authorization") String jwt){
        return symptomService.getSymptomById(id, jwt);
    }

    @GetMapping("/allSymptoms")
    public List<Symptom> getAllSymptoms(@RequestHeader("Authorization") String jwt){
        return symptomService.getAllSymptoms(jwt);
    }

    @PatchMapping("/{id}")
    public Symptom updateSymptom(@PathVariable Integer id, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt) {

        Symptom searchedSymptom = symptomService.getSymptomById(id, jwt);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Symptom.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, searchedSymptom, v);
        });

        return symptomService.update(id, searchedSymptom, jwt);
    }

    @DeleteMapping("/{name}")
    public void deleteSymptom(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        symptomService.delete(name, jwt);
    }
}
