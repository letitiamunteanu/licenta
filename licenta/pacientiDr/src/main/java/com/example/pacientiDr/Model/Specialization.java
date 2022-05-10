package com.example.pacientiDr.Model;

import javax.persistence.*;

@Entity
@Table(name = "specializari")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_specializare", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false, length = 45)
    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}