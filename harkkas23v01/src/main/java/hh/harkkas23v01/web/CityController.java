package hh.harkkas23v01.web;

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

import hh.harkkas23v01.domain.City;
import hh.harkkas23v01.domain.CityRepository;
import jakarta.validation.Valid;

@Controller //palauttaa HTML-sivun
public class CityController {


	@Autowired
	CityRepository cityrepository;

	//Listataan kaupungit
	@GetMapping("/cities")
	public String getCities(Model model) {
		System.out.println("--- LISTATAAN KAIKKI KAUPUNGIT --- CityController");
		model.addAttribute("cities", cityrepository.findAll());
		return "cities";
	}

	//Lisätään kaupunki, vain ADMIN  //09-11-2023
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus lisätä
	@GetMapping("/addCity")
	public String addCity(Model model) {
		model.addAttribute("city", new City());
		return "addCity";
	}

	
	//Tallennetaan kaupunki, ADMIN
	@PostMapping("/saveCity")
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus lisätä 09-11-2023
	public String saveCity(@Valid City city, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Virhe!");
			return "addCity";
		}

		System.out.println(" --- NEW CITY SAVED: " + city + " --- CityController");
		cityrepository.save(city);
		return "redirect:cities";
	}
	
	//Olemassa olevien kaupunkien muokkaus, ADMIN
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus
	@RequestMapping(value = "/editCity/{id}", method = RequestMethod.GET)
	public String editCity(@PathVariable("id") Long cityid, Model model) {
		model.addAttribute("city", cityrepository.findById(cityid)); 
		// model.addAttribute("cities", cityrepository.findAll());
		return "editCity";
	}
	
	
	// Kaupungin poistaminen, ADMIN
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus poistaa
	@GetMapping("deleteCity/{id}")
	public String deleteCity(@PathVariable("id") Long cityid, Model model) {
		System.out.println("--- POISTETTU KAUPUNKI, id " + cityid + " ---- CityController.java");
		if (cityrepository.findById(cityid).get().getArticles().isEmpty()) {
			cityrepository.deleteById(cityid);
		} else {
			System.out.println("EI VOI POISTAA, KAUPUNKIIN ON LIITETTY MYYTÄVIÄ TUOTTEITA, id" + cityid +" ---- CityController.java");
		}

		return "redirect:../cities";
	}

}

