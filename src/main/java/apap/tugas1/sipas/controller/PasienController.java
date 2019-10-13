package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.Pasien;
import apap.tugas1.sipas.repository.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @RequestMapping("/")
    public String beranda(Model model) {
        List<Pasien> pasienList = pasienService.getPasienList();
        model.addAttribute("pasienList", pasienList);
        return "beranda";
    }
}
