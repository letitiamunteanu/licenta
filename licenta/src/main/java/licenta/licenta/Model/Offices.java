package licenta.licenta.Model;

import javax.persistence.*;

@Table(name = "sedii", indexes = {
        @Index(name = "id_doctor_fk_idx", columnList = "id_doctor")
})
@Entity
public class Offices {
    @Id
    @Column(name = "id_sediu", nullable = false, length = 10)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_doctor", nullable = false)
    private Offices idDoctor;

    @Column(name = "locatie", nullable = false, length = 10)
    private String locatie;

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Offices getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Offices idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}