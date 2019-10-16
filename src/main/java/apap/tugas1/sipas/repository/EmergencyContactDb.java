package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactDb extends JpaRepository<EmergencyContact, Long> {

}
