package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.Asuransi;
import apap.tugas1.sipas.model.Pasien;

import java.util.List;

public interface PasienService {

    List<Pasien> getPasienList();

    Pasien addPasien(Pasien pasien, List<Asuransi> asuransiList);
}
