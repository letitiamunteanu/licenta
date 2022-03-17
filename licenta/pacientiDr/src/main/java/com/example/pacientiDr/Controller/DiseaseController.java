package com.example.pacientiDr.Controller;

import com.example.pacientiDr.Model.Disease;
import com.example.pacientiDr.Model.DiseaseSymptom;
import com.example.pacientiDr.POJO.SymptomToDiseasePOJO;
import com.example.pacientiDr.Services.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disease")
public class DiseaseController {

    private final DiseaseService diseaseService;

    @PostMapping("/addNewDisease")
    public Disease addNewDisease(@RequestBody Disease disease, @RequestHeader("Authorization") String jwt){
        return diseaseService.addNewDisease(disease, jwt);
    }

    @PostMapping("/addSymptomToDisease")
    public DiseaseSymptom addSymptomToDisease(@RequestBody SymptomToDiseasePOJO symptomToDisease){
        return diseaseService.symptomToDisease(symptomToDisease);
    }

    @GetMapping("/name/{name}")
    public Disease getDiseaseByName(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        return diseaseService.getDiseaseByName(name, jwt);
    }

    @GetMapping("/{id}")
    public Disease getDiseaseByName(@PathVariable Integer id, @RequestHeader("Authorization") String jwt){
        return diseaseService.getDiseaseById(id, jwt);
    }

    @GetMapping("/allDiseases")
    public List<Disease> getAllDiseases(@RequestHeader("Authorization") String jwt){
        return diseaseService.getAllDiseases(jwt);
    }

    @PatchMapping("/{name}")
    public Disease updateDisease(@PathVariable Integer id, @RequestBody Map<Object, Object> fields, @RequestHeader("Authorization") String jwt) {

        Disease searchedDisease = diseaseService.getDiseaseById(id, jwt);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Disease.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, searchedDisease, v);
        });

        return diseaseService.update(id, searchedDisease, jwt);
    }

    @DeleteMapping("/{name}")
    public void deleteDisease(@PathVariable String name, @RequestHeader("Authorization") String jwt){
        diseaseService.delete(name, jwt);
    }
}
