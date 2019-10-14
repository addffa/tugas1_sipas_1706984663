package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.Pasien;
import apap.tugas1.sipas.model.PasienAsuransi;

import java.util.List;

public interface PasienService {

    List<Pasien> getPasienList();

    void addPasien(Pasien pasien, PasienAsuransi pasienAsuransi);
}
