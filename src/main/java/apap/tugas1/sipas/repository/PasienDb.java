package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienDb extends JpaRepository<Pasien, Long> {
    Pasien findPasienByNik(String nik);

    @Query(
            "SELECT p FROM Pasien p " +
            "JOIN p.listAsuransi ap " +
            "JOIN p.listDiagnosisPenyakit dp " +
            "WHERE ap.asuransi.id = :idAsuransi AND dp.diagnosisPenyakit.id = :idDiagnosis")
    List<Pasien> findAllByIdAsuransiAndIdDiagnosis(@Param("idAsuransi") Long idAsuransi,
                                                   @Param("idDiagnosis") Long idDiagnosis);

    @Query(
            "SELECT count(p) FROM Pasien p " +
            "JOIN p.listDiagnosisPenyakit dp " +
            "WHERE dp.diagnosisPenyakit.id = :idDiagnosis AND p.jenisKelamin = :jenisKelamin")
    Long countPasienByJenisKelaminAndDiagnosisPenyakit(@Param("idDiagnosis") Long idDiagnosis,
                                                               @Param("jenisKelamin") Integer jenisKelamin);
}
