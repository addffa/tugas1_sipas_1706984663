package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.DiagnosisPenyakit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisPenyakitDb extends JpaRepository<DiagnosisPenyakit, Long> {

}
