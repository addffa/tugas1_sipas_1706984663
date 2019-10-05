package apap.tugas1.sipas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pasien_diagnosis_penyakit")
public class PasienDiagnosisPenyakit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Pasien pasien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_diagnosis_penyakit", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DiagnosisPenyakit diagnosisPenyakit;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_diagnosis", nullable = false)
    private Date tanggalDiagnosis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public DiagnosisPenyakit getDiagnosisPenyakit() {
        return diagnosisPenyakit;
    }

    public void setDiagnosisPenyakit(DiagnosisPenyakit diagnosisPenyakit) {
        this.diagnosisPenyakit = diagnosisPenyakit;
    }

    public Date getTanggalDiagnosis() {
        return tanggalDiagnosis;
    }

    public void setTanggalDiagnosis(Date tanggalDiagnosis) {
        this.tanggalDiagnosis = tanggalDiagnosis;
    }
}
