package hh.harkkas23v05.web;

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

import hh.harkkas23v05.domain.ApplicationUserRepository;
import hh.harkkas23v05.domain.Memo;
import hh.harkkas23v05.domain.MemoRepository;
import jakarta.validation.Valid;

@Controller
public class MemoController {
	
	@Autowired
	private ApplicationUserRepository appuserRepository;
	
	@Autowired
	private MemoRepository memoRepository; 
	
	//Kaikki memot
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value = {"/mymemos"}) //endpoint:  http://localhost:8080/mymemos
	public String memoList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		System.out.println("--- KAIKKI KÄYTTÄJÄN MUISTIINPANOT --- MemoController");
		model.addAttribute("memos", memoRepository.findAll());
		
		/* Näkyvyyden rajaaminen on määritelty myös mymemos.html -sivulla käyttäjänimen ja roolin perusteella.
		 * Jokainen käyttäjä näkee vain omat muistiinpanonsa ja roolin täytyy olla USER tai ADMIN
		 */
		model.addAttribute("applicationuser", appuserRepository.findByUsername(userDetails.getUsername())); 
		
		return "mymemos.html";	
	}


	
	//Lisätään memo
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/addMemo")
	public String addMemo(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		
		model.addAttribute("memo", new Memo());
		
		model.addAttribute("applicationuser", appuserRepository.findByUsername(userDetails.getUsername())); 
		
		return "addMemo";
	}
	
	//Tallennetaan memo
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/saveMemo", method = RequestMethod.POST) //tämä  endpoint käytössä addMemo.html :ssä
	public String save(@Valid Memo memo, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some validation error happened");

			model.addAttribute("memo", memo);			
			model.addAttribute("applicationuser", appuserRepository.findByUsername(userDetails.getUsername())); //käytetään addMemo.html:ssä
			return "addMemo";
		}
		memoRepository.save(memo);
		System.out.println("--- LISÄTTY MEMO: " + memo + memo.getMemocontent() + " --- MemoController");
		System.out.println("Lisääjän käyttäjätunnus: " + memo.getApplicationuser().getUsername());
		return "redirect:/mymemos";
	}
	
	//Poista memo
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/deleteMemo/{id}", method=RequestMethod.GET)
	public String deleteMemo(@PathVariable("id") Long memoId, Model model) {
		memoRepository.deleteById(memoId);
		System.out.println("--- POISTETAAN MEMO, id: " + memoId +" --- MemoController");
		return "redirect:../mymemos"; 
	}
	
	//Muokkaa memoa
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value= "/editMemo/{id}", method = RequestMethod.GET)
	public String editMemo(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		System.out.println(memoRepository.findById(id));
		model.addAttribute("memo", memoRepository.findById(id)); //memoid
		
		//model.addAttribute("applicationusers", appurepository.findAll()); //Tämä tarvitaan jos käyttäjät listataan editMemo.html:ssä
		return "editMemo";
	}
	
}
