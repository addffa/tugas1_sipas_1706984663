package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.Asuransi;
import apap.tugas1.sipas.model.DiagnosisPenyakit;
import apap.tugas1.sipas.model.Pasien;
import apap.tugas1.sipas.model.PasienAsuransi;
import apap.tugas1.sipas.service.AsuransiService;
import apap.tugas1.sipas.service.DiagnosisPenyakitService;
import apap.tugas1.sipas.service.PasienService;
import apap.tugas1.sipas.util.KodePasienGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @RequestMapping("/")
    public String beranda(Model model) {
        List<Pasien> pasienList = pasienService.getPasienList();
        model.addAttribute("pasienList", pasienList);
        return "beranda";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String formTambahPasien(Model model) {
        Pasien newPasien = new Pasien();
        newPasien.setListAsuransi(new ArrayList<>());
        newPasien.getListAsuransi().add(new PasienAsuransi());
        model.addAttribute("pasien", newPasien);
        model.addAttribute("asuransiList", asuransiService.getAsuransiList());
        return "form-tambah-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST, params = {"tambahAsuransi"})
    public String tambahAsuransi(@ModelAttribute Pasien newPasien, Model model) {
        newPasien.getListAsuransi().add(new PasienAsuransi());
        model.addAttribute("pasien", newPasien);
        model.addAttribute("asuransiList", asuransiService.getAsuransiList());
        return "form-tambah-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST, params = {"tambahPasien"})
    public String tambahPasien(@ModelAttribute Pasien newPasien, Model model) {
        List<Asuransi> asuransiList = new ArrayList<>();
        for(PasienAsuransi pasienAsuransi : newPasien.getListAsuransi()) {
            asuransiList.add(asuransiService.getAsuransiById(pasienAsuransi.getAsuransi() .getId()));
        }
        newPasien = pasienService.addPasien(newPasien, asuransiList);
        model.addAttribute("pasien", newPasien);
        return "tambah-pasien";
    }

    @RequestMapping(value = "/pasien", method = RequestMethod.GET)
    public String detailPasien(@RequestParam(name = "nikPasien") String nik, Model model) {
        Pasien pasien = pasienService.getPasien(nik);
        model.addAttribute("pasien", pasien);
        return "detail-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.GET)
    public String formUbahPasien(@PathVariable(value = "nikPasien") String nik, Model model) {
        Pasien pasien = pasienService.getPasien(nik);
        model.addAttribute("pasien", pasien);
        return "form-ubah-pasien";
    }

    @RequestMapping(value = "/pasien/ubah/{nikPasien}", method = RequestMethod.POST)
    public String ubahPasien(@PathVariable(value = "nikPasien") String nik, @ModelAttribute Pasien pasien,Model model) {
        Pasien newPasien = pasienService.changePasien(nik, pasien);
        model.addAttribute("pasien", newPasien);
        return "ubah-pasien";
    }

    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.GET)
    public String formPasienTambahDiagnosisPenyakit(@PathVariable(value = "nikPasien") String nik, Model model) {
        model.addAttribute("pasien", pasienService.getPasien(nik));
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitService.getDiagnosisPenyakitList());
        model.addAttribute("diagnosisPenyakit", new DiagnosisPenyakit());
        model.addAttribute("submitted", false);
        return "form-pasien-tambah-diagnosis-penyakit";
    }

    @RequestMapping(value = "/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.POST)
    public String pasienTambahDiagnosisPenyakit(@PathVariable(value = "nikPasien") String nik,
                                                @ModelAttribute DiagnosisPenyakit diagnosisPenyakit,
                                                Model model) {
        pasienService.addDiagnosisPenyakitPasien(nik, diagnosisPenyakit.getId());
        model.addAttribute("pasien", pasienService.getPasien(nik));
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitService.getDiagnosisPenyakitList());
        model.addAttribute("diagnosisPenyakit", new DiagnosisPenyakit());
        model.addAttribute("submitted", true);
        return "form-pasien-tambah-diagnosis-penyakit";
    }

    @RequestMapping(value = "/pasien/cari", method = RequestMethod.GET)
    public String cariPasienBerdasarkanAsuransiDiagnosis(@RequestParam(name = "idAsuransi", required = false) Long idAsuransi,
                                                         @RequestParam(name = "idDiagnosis", required = false) Long idDiagnosis,
                                                         Model model) {
        model.addAttribute("asuransiList", asuransiService.getAsuransiList());
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitService.getDiagnosisPenyakitList());
        model.addAttribute("idAsuransi", idAsuransi);
        model.addAttribute("idDiagnosis", idDiagnosis);
        List<Pasien> result;
        if(idAsuransi == null && idDiagnosis == null) {
            result = null;
        } else if(idDiagnosis == null) {
            result = pasienService.getPasienByAsuransi(idAsuransi);
        } else if(idAsuransi == null) {
            result = pasienService.getPasienByDiagnosisPenyakit(idDiagnosis);
        } else {
            result = pasienService.getPasienByAsuransiAndDiagnosisPenyakit(idAsuransi, idDiagnosis);
        }
        model.addAttribute("result", result);
        return "form-cari-pasien-asuransi-diagnosis";
    }

    @RequestMapping(value = "/pasien/cari/lakilaki-perempuan", method = RequestMethod.GET)
    public String hitungJumlahPasienBerdasarkanDiagnosis(
            @RequestParam(value = "idDiagnosis", required = false) Long idDiagnosis,
            Model model) {
        if(idDiagnosis != null) {
            model.addAttribute("diagnosisPenyakit", diagnosisPenyakitService.getDiagnosisPenyakit(idDiagnosis));
            model.addAttribute("lakiLaki", pasienService.countPasienByJenisKelaminAndDiagnosisPenyakit(idDiagnosis, 1));
            model.addAttribute("perempuan", pasienService.countPasienByJenisKelaminAndDiagnosisPenyakit(idDiagnosis, 2));
        }
        model.addAttribute("idDiagnosis", idDiagnosis);
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitService.getDiagnosisPenyakitList());
        return "jumlah-pasien-diagnosis";
    }
}
