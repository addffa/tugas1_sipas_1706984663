package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.DiagnosisPenyakit;

import java.util.List;

public interface DiagnosisPenyakitService {

    List<DiagnosisPenyakit> getDiagnosisPenyakitList();

    DiagnosisPenyakit getDiagnosisPenyakit(Long id);

    void addDiagnosisPenyakit(DiagnosisPenyakit diagnosisPenyakit);
}
