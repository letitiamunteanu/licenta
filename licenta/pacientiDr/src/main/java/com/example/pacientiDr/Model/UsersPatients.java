package com.example.pacientiDr.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users_pacienti")
public class UsersPatients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_pacient", nullable = false)
    private Integer idPacient;

    @Column(name = "id_username", nullable = false, length = 25)
    private String idUsername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(Integer idPacient) {
        this.idPacient = idPacient;
    }

    public String getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

}