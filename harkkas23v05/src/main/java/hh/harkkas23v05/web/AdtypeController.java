package hh.harkkas23v05.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.harkkas23v05.domain.Adtype;
import hh.harkkas23v05.domain.AdtypeRepository;
import jakarta.validation.Valid;

@Controller //palauttaa HTML-sivun
public class AdtypeController {


	@Autowired
	AdtypeRepository adtyperepository;

	//Listataan ilmoitustyypit
	@GetMapping("/adtypes")
	public String getCities(Model model) {
		System.out.println("--- LISTATAAN KAIKKI ILMOITUSTYYPIT --- AdtypeController");
		model.addAttribute("adtypes", adtyperepository.findAll());
		return "adtypes";
	}

	//Lisätään ilmoitustyyppi, vain ADMIN  //09-11-2023
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus lisätä
	@GetMapping("/addAdtype")
	public String addAdtype(Model model) {
		model.addAttribute("adtype", new Adtype());
		return "addAdtype";
	}

	
	//Tallennetaan ilmoitustyyppi, ADMIN
	@PostMapping("/saveAdtype")
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus lisätä 09-11-2023
	public String saveAdtype(@Valid Adtype adtype, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Virhe!");
			return "addAdtype";
		}

		System.out.println(" --- UUSI ILMOITUSTYYPPI TALLENNETTU: " + adtype + " --- AdtypeController");
		adtyperepository.save(adtype);
		return "redirect:adtypes";
	}
	
	//Olemassa olevien ilmoitustyyppien muokkaus, ADMIN
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus
	@RequestMapping(value = "/editAdtype/{id}", method = RequestMethod.GET)
	public String editAdtype(@PathVariable("id") Long adtypeid, Model model) {
		model.addAttribute("adtype", adtyperepository.findById(adtypeid)); 
		// model.addAttribute("adtypes", adtyperepository.findAll());
		return "editAdtype";
	}
	
	
	// Ilmoitustyypin poistaminen, ADMIN
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus poistaa
	@GetMapping("deleteAdtype/{id}")
	public String deleteAdtype(@PathVariable("id") Long adtypeid, Model model) {
		System.out.println("--- POISTETTU ILMOITUSTYYPPI, id " + adtypeid + " ---- AdtypeController.java");
		if (adtyperepository.findById(adtypeid).get().getArticles().isEmpty()) {
			adtyperepository.deleteById(adtypeid);
		} else {
			System.out.println("EI VOI POISTAA, ILMOITUSTYYPPIIN ON LIITETTY MYYTÄVIÄ TUOTTEITA, id" + adtypeid +" ---- AdtypeController.java");
		}

		return "redirect:../adtypes";
	}

}

