package licenta.licenta.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "diagnostice")
@Entity
public class Diagnostics {
    @Id
    @Column(name = "id_diagnostic", nullable = false, length = 45)
    private String id;

    @Column(name = "gen", nullable = false, length = 1)
    private String gen;

    @Column(name = "varsta", nullable = false)
    private Integer varsta;

    @Column(name = "diagnostic", nullable = false, length = 200)
    private String diagnostic;

    @Column(name = "simptome", nullable = false, length = 500)
    private String simptome;

    @Column(name = "boli", length = 200)
    private String boli;

    @Column(name = "alergii", length = 200)
    private String alergii;

    @Column(name = "grupa_sange", nullable = false, length = 5)
    private String grupaSange;

    public String getGrupaSange() {
        return grupaSange;
    }

    public void setGrupaSange(String grupaSange) {
        this.grupaSange = grupaSange;
    }

    public String getAlergii() {
        return alergii;
    }

    public void setAlergii(String alergii) {
        this.alergii = alergii;
    }

    public String getBoli() {
        return boli;
    }

    public void setBoli(String boli) {
        this.boli = boli;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}