package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.DiagnosisPenyakit;
import apap.tugas1.sipas.service.DiagnosisPenyakitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiagnosisPenyakitController {

    @Autowired
    DiagnosisPenyakitService diagnosisPenyakitService;

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String daftarDiagnosisPenyakit(Model model) {
        model.addAttribute("diagnosisPenyakitList", diagnosisPenyakitService.getDiagnosisPenyakitList());
        return "daftar-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit", method = RequestMethod.GET)
    public String detailDiagnosisPenyakit(@RequestParam(name = "idDiagnosis") Long id, Model model) {
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakitService.getDiagnosisPenyakit(id));
        return "detail-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String formTambahDiagnosisPenyakit(Model model) {
        model.addAttribute("diagnosisPenyakit", new DiagnosisPenyakit());
        return "form-tambah-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.POST)
    public String tambahDiagnosisPenyakit(@ModelAttribute DiagnosisPenyakit diagnosisPenyakit) {
        diagnosisPenyakitService.addDiagnosisPenyakit(diagnosisPenyakit);
        return "tambah-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/hapus/{idDiagnosis}")
    public String hapusDiagnosisPenyakit(@PathVariable(value = "idDiagnosis") Long idDiagnosis, Model model) {
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakitService.getDiagnosisPenyakit(idDiagnosis));
        boolean deleted = diagnosisPenyakitService.deleteDiagnosisPenyakit(idDiagnosis);
        model.addAttribute("isDeleted", deleted);
        return "hapus-diagnosis-penyakit";
    }
}
