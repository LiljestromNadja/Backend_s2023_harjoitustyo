package hh.harkkas23v04.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.harkkas23v04.domain.AdtypeRepository;
import hh.harkkas23v04.domain.ApplicationUserRepository;
import hh.harkkas23v04.domain.Article;
import hh.harkkas23v04.domain.ArticleRepository;
import jakarta.validation.Valid;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository articlerepository;
	
	@Autowired
	private AdtypeRepository adtyperepository;
	
	@Autowired
	private ApplicationUserRepository appurepository; //09-11-2023 käytetään kommentin lisäämisessä, myytävän tuotteen lisäämisessä/muokkaamisessa
	
	

	
	//Main/index
	@RequestMapping(value= {"/", "/main" , "/index"}) //Eendpoint: http://localhost:8080 , http://localhost:8080/index ,http://localhost:8080/main
	public String showMainPage() {
		return "index";  //index.html
	}
	
	//Kaikki myytävät tuotteet	
	@RequestMapping(value = {"/articlelist"}) //endpoint:  http://localhost:8080/articlelist
	public String articleList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		System.out.println("--- NÄYTETÄÄN KAIKKI MYYTÄVÄT TUOTTEET --- ArticleController");
		model.addAttribute("articles", articlerepository.findAll());
		
		/*Kun halutaan rajata näkyvyyttä niin että käyttäjä USER-roolissa näkee vain itse lisäämiensä myynti-ilmoitusten 
		 * edit ja delete-napin. ADMIN voi muokata/poistaa kaikkia. 
		 * - Jos tätä muutetaan, niin täytyy muuttaa myös articlelist.html 
		 * - Lisääminen ja poistaminen vaativat USER tai ADMIN-roolin		
		 */
		model.addAttribute("applicationuser", appurepository.findByUsername(userDetails.getUsername())); 
		
		return "articlelist.html";	
	}


	//Lisätään myytävä tuote/tavara
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/add")
	public String addArticle(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		
		model.addAttribute("article", new Article());
		model.addAttribute("adtypes", adtyperepository.findAll());
		model.addAttribute("applicationuser", appurepository.findByUsername(userDetails.getUsername())); 
		
		return "addArticle";
	}
	
	//Tallennetaan myytävä tuote/tavara
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/save", method = RequestMethod.POST) //tämä  endpoint käytössä addArticle.html :ssä
	public String save(@Valid Article article, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");
			model.addAttribute("article", article);
			model.addAttribute("adtypes", adtyperepository.findAll()); 
			model.addAttribute("applicationuser", appurepository.findByUsername(userDetails.getUsername())); //käytetään addArticle.html:ssä
			return "addArticle";
		}
		articlerepository.save(article);
		System.out.println("--- LISÄTTY: " + article + article.getAdtype() + " --- ArticleController");
		System.out.println("Lisääjän käyttäjätunnus: " + article.getApplicationuser().getUsername());
		return "redirect:/articlelist";
	}

	//Poista tuote/tavara
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteArticle(@PathVariable("id") Long articleId, Model model) {
		articlerepository.deleteById(articleId);
		System.out.println("--- POISTETAAN MYYTÄVÄ TUOTE, id: " + articleId +" --- ArticleController");
		return "redirect:../articlelist"; 
	}
	

	//Muokkaa myytävää tuotetta 
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value= "/editArticle/{id}", method = RequestMethod.GET)
	public String editArticle(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		System.out.println(articlerepository.findById(id));
		model.addAttribute("article", articlerepository.findById(id)); //articleid
		model.addAttribute("adtypes", adtyperepository.findAll());
		//model.addAttribute("applicationusers", appurepository.findAll()); //Tämä tarvitaan jos käyttäjät listataan editArticle.html:ssä
		return "editArticle";
	}

}
