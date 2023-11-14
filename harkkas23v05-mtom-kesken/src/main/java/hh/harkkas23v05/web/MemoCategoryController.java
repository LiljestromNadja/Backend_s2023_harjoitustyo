package hh.harkkas23v05.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.harkkas23v05.domain.ApplicationUserRepository;
import hh.harkkas23v05.domain.CategoryRepository;
import hh.harkkas23v05.domain.Memo;
import hh.harkkas23v05.domain.MemoRepository;

@Controller
@RequestMapping("/memosviacategories")
public class MemoCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private MemoRepository memoRepository; 
	
	@Autowired
	private ApplicationUserRepository appuserRepository;
	
	
	//Lisätään memo
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/add")
	public String showForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		model.addAttribute("memo", new Memo());
		model.addAttribute("categories", categoryRepository.findAll());

		//model.addAttribute("applicationuser", appuserRepository.findByUsername(userDetails.getUsername())); 
		
		return "addMemoWithCategory";
	}
	
	//tallennetaan
	@RequestMapping(value="/save", method = RequestMethod.POST) 
	public String saveMemo(@ModelAttribute("memo") Memo memo) {

		memoRepository.save(memo);
		return "redirect:/memosviacategories/memos";
	}
	
	//KESKEN! 

}
