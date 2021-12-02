package licenta.licenta.Model;

import javax.persistence.*;

@Table(name = "doctori", indexes = {
        @Index(name = "_idx", columnList = "sediu")
})
@Entity
public class Doctors {
    @Id
    @Column(name = "id_doctor", nullable = false, length = 10)
    private String id;

    @Column(name = "nume", nullable = false, length = 45)
    private String nume;

    @Column(name = "prenume", nullable = false, length = 45)
    private String prenume;

    @Column(name = "specializare", nullable = false, length = 45)
    private String specializare;

    @Column(name = "sala", nullable = false, length = 45)
    private String sala;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sediu", nullable = false)
    private Offices sediu;

    public Offices getSediu() {
        return sediu;
    }

    public void setSediu(Offices sediu) {
        this.sediu = sediu;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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