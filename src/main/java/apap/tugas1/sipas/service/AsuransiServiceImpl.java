package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.Asuransi;
import apap.tugas1.sipas.repository.AsuransiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsuransiServiceImpl implements AsuransiService {

    @Autowired
    AsuransiDb asuransiDb;

    @Override
    public List<Asuransi> getAsuransiList() {
        return asuransiDb.findAll();
    }

    @Override
    public Asuransi getAsuransiById(Long id) {
        return asuransiDb.findById(id).get();
    }
}
