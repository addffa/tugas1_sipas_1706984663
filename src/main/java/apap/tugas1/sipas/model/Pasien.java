package apap.tugas1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pasien")
public class Pasien implements Serializable {

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

    @NotNull
    @Size(max = 255)
    @Column(name = "nik", nullable = false)
    private String nik;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<PasienDiagnosisPenyakit> listDiagnosisPenyakit;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienAsuransi> listAsuransi;

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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public List<PasienDiagnosisPenyakit> getListDiagnosisPenyakit() {
        return listDiagnosisPenyakit;
    }

    public void setListDiagnosisPenyakit(List<PasienDiagnosisPenyakit> listDiagnosisPenyakit) {
        this.listDiagnosisPenyakit = listDiagnosisPenyakit;
    }

    public List<PasienAsuransi> getListAsuransi() {
        return listAsuransi;
    }

    public void setListAsuransi(List<PasienAsuransi> listAsuransi) {
        this.listAsuransi = listAsuransi;
    }
}
