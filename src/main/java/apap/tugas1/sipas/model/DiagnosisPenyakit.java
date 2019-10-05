package apap.tugas1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "diagnosis_penyakit")
public class DiagnosisPenyakit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode", nullable = false)
    private String kode;

    @OneToMany(mappedBy = "diagnosisPenyakit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakit> listPasien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<PasienDiagnosisPenyakit> getListPasien() {
        return listPasien;
    }

    public void setListPasien(List<PasienDiagnosisPenyakit> listPasien) {
        this.listPasien = listPasien;
    }
}
