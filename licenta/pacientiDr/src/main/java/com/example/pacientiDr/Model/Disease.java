package com.example.pacientiDr.Model;

import javax.persistence.*;

@Entity
@Table(name = "boli")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boala", nullable = false)
    private Integer id;

    @Column(name = "nume_boala", nullable = false, length = 100)
    private String numeBoala;

    public String getNumeBoala() {
        return numeBoala;
    }

    public void setNumeBoala(String numeBoala) {
        this.numeBoala = numeBoala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}