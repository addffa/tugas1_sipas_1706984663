package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.DiagnosisPenyakit;
import apap.tugas1.sipas.repository.DiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiagnosisPenyakitServiceImpl implements DiagnosisPenyakitService {

    @Autowired
    DiagnosisPenyakitDb diagnosisPenyakitDb;

    @Override
    public List<DiagnosisPenyakit> getDiagnosisPenyakitList() {
        return diagnosisPenyakitDb.findAll();
    }

    @Override
    public DiagnosisPenyakit getDiagnosisPenyakit(Long id) {
        return diagnosisPenyakitDb.findById(id).get();
    }

    @Override
    public void addDiagnosisPenyakit(DiagnosisPenyakit diagnosisPenyakit) {
        DiagnosisPenyakit newDiagnosisPenyakit = new DiagnosisPenyakit();
        newDiagnosisPenyakit.setNama(diagnosisPenyakit.getNama());
        newDiagnosisPenyakit.setKode(diagnosisPenyakit.getKode());
        diagnosisPenyakitDb.save(newDiagnosisPenyakit);
    }

    @Override
    public boolean deleteDiagnosisPenyakit(Long id) {
        DiagnosisPenyakit diagnosisPenyakit = diagnosisPenyakitDb.findById(id).get();
        if(diagnosisPenyakit.getListPasien().isEmpty()) {
            diagnosisPenyakitDb.deleteById(id);
            return true;
        }
        return false;
    }
}
