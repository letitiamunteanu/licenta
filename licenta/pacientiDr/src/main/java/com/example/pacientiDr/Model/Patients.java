package com.example.pacientiDr.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacienti")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false, length = 45)
    private String nume;

    @Column(name = "prenume", nullable = false, length = 45)
    private String prenume;

    @Column(name = "cnp", nullable = false, length = 13)
    private String cnp;

    @Column(name = "data_nastere", nullable = false)
    private LocalDate dataNastere;

    @Column(name = "varsta", nullable = false)
    private Integer varsta;

    @Column(name = "grupa_sange", nullable = false, length = 5)
    private String grupaSange;

    @Column(name = "greutate", nullable = false)
    private Integer greutate;

    @Column(name = "nr_telefon", length = 10)
    private String nrTelefon;

    @Column(name = "simptome")
    private String simptome;

    @Column(name = "specificatii")
    private String specificatii;

    @Column(name = "inaltime", nullable = false)
    private Integer inaltime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public LocalDate getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(LocalDate dataNastere) {
        this.dataNastere = dataNastere;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public String getGrupaSange() {
        return grupaSange;
    }

    public void setGrupaSange(String grupaSange) {
        this.grupaSange = grupaSange;
    }

    public Integer getGreutate() {
        return greutate;
    }

    public void setGreutate(Integer greutate) {
        this.greutate = greutate;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    public String getSpecificatii() {
        return specificatii;
    }

    public void setSpecificatii(String specificatii) {
        this.specificatii = specificatii;
    }

    public Integer getInaltime() {
        return inaltime;
    }

    public void setInaltime(Integer inaltime) {
        this.inaltime = inaltime;
    }

}