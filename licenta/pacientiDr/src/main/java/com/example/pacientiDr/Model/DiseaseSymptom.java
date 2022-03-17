package com.example.pacientiDr.Model;

import javax.persistence.*;

@Entity
@Table(name = "boli_simptome")
public class DiseaseSymptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boli_simptome", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_boala", nullable = false)
    private Disease boli;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_simptom", nullable = false)
    private Symptom simptome;

    public Symptom getSimptome() {
        return simptome;
    }

    public void setSimptome(Symptom simptome) {
        this.simptome = simptome;
    }

    public Disease getBoli() {
        return boli;
    }

    public void setBoli(Disease boli) {
        this.boli = boli;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}