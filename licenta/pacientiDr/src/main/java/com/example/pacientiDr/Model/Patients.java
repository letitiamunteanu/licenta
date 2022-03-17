package com.example.pacientiDr.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pacienti")
public class Patients {
    @Id
    @Column(name = "id_pacient", nullable = false, length = 20)
    private String id;

    @Column(name = "nume", nullable = false, length = 20)
    private String nume;

    @Column(name = "prenume", nullable = false, length = 20)
    private String prenume;

    @Column(name = "cnp", nullable = false, length = 13)
    private String cnp;

    @Column(name = "data_nastere", nullable = false, length = 20)
    private String dataNastere;

    @Column(name = "varsta", nullable = false)
    private Integer varsta;

    @Column(name = "grupa_sange", nullable = false, length = 5)
    private String grupaSange;

    @Column(name = "greutate", nullable = false)
    private Integer greutate;

    @Column(name = "inaltime", nullable = false)
    private Integer inaltime;

    @Column(name = "nr_telefon", length = 10)
    private String nrTelefon;

    @Column(name = "simptome", length = 200)
    private String simptome;

    @Column(name = "specificatii", length = 200)
    private String specificatii;

    public String getSpecificatii() {
        return specificatii;
    }

    public void setSpecificatii(String specificatii) {
        this.specificatii = specificatii;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public Integer getInaltime() {
        return inaltime;
    }

    public void setInaltime(Integer inaltime) {
        this.inaltime = inaltime;
    }

    public Integer getGreutate() {
        return greutate;
    }

    public void setGreutate(Integer greutate) {
        this.greutate = greutate;
    }

    public String getGrupaSange() {
        return grupaSange;
    }

    public void setGrupaSange(String grupaSange) {
        this.grupaSange = grupaSange;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
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