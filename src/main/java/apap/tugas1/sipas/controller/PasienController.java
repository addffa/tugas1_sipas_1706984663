package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.Asuransi;
import apap.tugas1.sipas.model.Pasien;
import apap.tugas1.sipas.model.PasienAsuransi;
import apap.tugas1.sipas.service.AsuransiService;
import apap.tugas1.sipas.service.PasienService;
import apap.tugas1.sipas.util.KodePasienGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

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

}
