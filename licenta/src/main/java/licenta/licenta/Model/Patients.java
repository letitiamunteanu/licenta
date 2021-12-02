package licenta.licenta.Model;

import javax.persistence.*;

@Table(name = "pacienti", indexes = {
        @Index(name = "cnp_UNIQUE", columnList = "cnp", unique = true)
})
@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pacient", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false, length = 45)
    private String nume;

    @Column(name = "prenume", nullable = false, length = 45)
    private String prenume;

    @Column(name = "varsta", nullable = false)
    private Integer varsta;

    @Column(name = "greutate", nullable = false)
    private Integer greutate;

    @Column(name = "inaltime", nullable = false)
    private Integer inaltime;

    @Column(name = "domiciliu", length = 100)
    private String domiciliu;

    @Column(name = "nr_telefon", nullable = false, length = 45)
    private String nrTelefon;

    @Column(name = "simptome", nullable = false, length = 200)
    private String simptome;

    @Column(name = "grupa_sange", nullable = false, length = 5)
    private String grupaSange;

    @Column(name = "diagnostic", length = 200)
    private String diagnostic;

    @Column(name = "specificatii", length = 200)
    private String specificatii;

    @Column(name = "cnp", nullable = false, length = 13)
    private String cnp;

    @Column(name = "data_nastere", nullable = false, length = 10)
    private String dataNastere;

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

    public String getSpecificatii() {
        return specificatii;
    }

    public void setSpecificatii(String specificatii) {
        this.specificatii = specificatii;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getGrupaSange() {
        return grupaSange;
    }

    public void setGrupaSange(String grupaSange) {
        this.grupaSange = grupaSange;
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

    public String getDomiciliu() {
        return domiciliu;
    }

    public void setDomiciliu(String domiciliu) {
        this.domiciliu = domiciliu;
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

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}