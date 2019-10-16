package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.Asuransi;

import java.util.List;

public interface AsuransiService {

    List<Asuransi> getAsuransiList();

    Asuransi getAsuransiById(Long id);
}
