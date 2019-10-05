package apap.tugas1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "asuransi")
public class Asuransi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis", nullable = false)
    private String jenis;

    @OneToMany(mappedBy = "asuransi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PasienAsuransi> listPasien;

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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public List<PasienAsuransi> getListPasien() {
        return listPasien;
    }

    public void setListPasien(List<PasienAsuransi> listPasien) {
        this.listPasien = listPasien;
    }
}
