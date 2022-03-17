package com.example.pacientiDr.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctori")
public class Doctors {
    @Id
    @Column(name = "cuim", nullable = false, length = 15)
    private String id;

    @Column(name = "nume", nullable = false, length = 15)
    private String nume;

    @Column(name = "prenume", nullable = false, length = 15)
    private String prenume;

    @Column(name = "specializare", nullable = false, length = 25)
    private String specializare;

    @Column(name = "sediu", nullable = false, length = 25)
    private String sediu;

    @Column(name = "sala", nullable = false, length = 10)
    private String sala;

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getSediu() {
        return sediu;
    }

    public void setSediu(String sediu) {
        this.sediu = sediu;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}