package com.example.pacientiDr.Model;

import javax.persistence.*;

@Entity
@Table(name = "users_doctori")
public class UsersDoctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_cuim", nullable = false, length = 15)
    private String idCuim;

    @Column(name = "id_username", nullable = false, length = 25)
    private String idUsername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCuim() {
        return idCuim;
    }

    public void setIdCuim(String idCuim) {
        this.idCuim = idCuim;
    }

    public String getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

}