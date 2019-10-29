package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.*;
import apap.tugas1.sipas.repository.DiagnosisPenyakitDb;
import apap.tugas1.sipas.repository.EmergencyContactDb;
import apap.tugas1.sipas.repository.PasienAsuransiDb;
import apap.tugas1.sipas.repository.PasienDb;
import apap.tugas1.sipas.service.PasienService;
import apap.tugas1.sipas.util.KodePasienGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {

    @Autowired
    PasienDb pasienDb;

    @Autowired
    PasienAsuransiDb pasienAsuransiDb;

    @Autowired
    EmergencyContactDb emergencyContactDb;

    @Autowired
    DiagnosisPenyakitDb diagnosisPenyakitDb;

    @Override
    public List<Pasien> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public Pasien addPasien(Pasien pasien, List<Asuransi> asuransiList) {
        Pasien newPasien = new Pasien();
        newPasien.setKode(KodePasienGenerator.generate(pasien.getTanggalLahir(), pasien.getJenisKelamin()));
        newPasien.setJenisKelamin(pasien.getJenisKelamin());
        newPasien.setNama(pasien.getNama());
        newPasien.setNik(pasien.getNik());
        newPasien.setTanggalLahir(pasien.getTanggalLahir());
        newPasien.setTempatLahir(pasien.getTempatLahir());
        newPasien = pasienDb.save(newPasien);

        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setNama(pasien.getEmergencyContact().getNama());
        emergencyContact.setNoHp(pasien.getEmergencyContact().getNoHp());
        emergencyContact.setNik(pasien.getEmergencyContact().getNik());
        emergencyContact.setPasien(newPasien);
        emergencyContactDb.save(emergencyContact);

        for(Asuransi asuransi : asuransiList) {
            PasienAsuransi newPasienAsuransi = new PasienAsuransi();
            newPasienAsuransi.setPasien(newPasien);
            newPasienAsuransi.setAsuransi(asuransi);
            pasienAsuransiDb.save(newPasienAsuransi);
        }
        return newPasien;
    }

    @Override
    public Pasien getPasien(String nik) {
        return pasienDb.findPasienByNik(nik);
    }

    @Override
    public Pasien changePasien(String nik, Pasien pasien) {
        Pasien targetPasien = pasienDb.findPasienByNik(nik);
        targetPasien.setKode(KodePasienGenerator.change(targetPasien.getKode(), pasien.getTanggalLahir(), pasien.getJenisKelamin()));
        targetPasien.setNama(pasien.getNama());
        targetPasien.setNik(pasien.getNik());
        targetPasien.setJenisKelamin(pasien.getJenisKelamin());
        targetPasien.setTanggalLahir(pasien.getTanggalLahir());
        targetPasien.setTempatLahir(pasien.getTempatLahir());
        pasienDb.save(targetPasien);

        EmergencyContact emergencyContact = targetPasien.getEmergencyContact();
        emergencyContact.setNama(pasien.getEmergencyContact().getNama());
        emergencyContact.setNik(pasien.getEmergencyContact().getNik());
        emergencyContact.setNoHp(pasien.getEmergencyContact().getNoHp());
        emergencyContactDb.save(emergencyContact);
        return targetPasien;
    }

    @Override
    public void addDiagnosisPenyakitPasien(String nikPasien, Long idDiagnosis) {
        Pasien pasien = pasienDb.findPasienByNik(nikPasien);
        DiagnosisPenyakit diagnosisPenyakit = diagnosisPenyakitDb.findById(idDiagnosis).get();
        PasienDiagnosisPenyakit diagnosisPenyakitPasien = new PasienDiagnosisPenyakit();
        diagnosisPenyakitPasien.setPasien(pasien);
        diagnosisPenyakitPasien.setDiagnosisPenyakit(diagnosisPenyakit);
        diagnosisPenyakitPasien.setTanggalDiagnosis(new Date());
        pasien.getListDiagnosisPenyakit().add(diagnosisPenyakitPasien);
        pasienDb.save(pasien);
    }
}
