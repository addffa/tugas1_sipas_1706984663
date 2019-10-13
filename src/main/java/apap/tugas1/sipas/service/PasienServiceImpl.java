package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.Pasien;
import apap.tugas1.sipas.repository.PasienDb;
import apap.tugas1.sipas.repository.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {

    @Autowired
    PasienDb pasienDb;

    @Override
    public List<Pasien> getPasienList() {
        return pasienDb.findAll();
    }
}
