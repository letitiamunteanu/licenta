package com.example.pacientiDr.Model;

import javax.persistence.*;

@Entity
@Table(name = "simptome")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_simptom", nullable = false)
    private Integer id;

    @Column(name = "nume_simptom", nullable = false, length = 45)
    private String numeSimptom;

    public String getNumeSimptom() {
        return numeSimptom;
    }

    public void setNumeSimptom(String numeSimptom) {
        this.numeSimptom = numeSimptom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}