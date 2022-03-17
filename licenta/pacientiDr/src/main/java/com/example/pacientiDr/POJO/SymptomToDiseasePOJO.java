package com.example.pacientiDr.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SymptomToDiseasePOJO {

    private final String symptomName;
    private final String diseaseName;
}
