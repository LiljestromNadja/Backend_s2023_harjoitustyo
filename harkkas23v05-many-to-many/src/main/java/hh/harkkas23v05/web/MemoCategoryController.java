package hh.harkkas23v05.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.harkkas23v05.domain.CategoryRepository;
import hh.harkkas23v05.domain.MemoCategory;
import hh.harkkas23v05.domain.MemoCategoryRepository;
import hh.harkkas23v05.domain.MemoRepository;

@Controller
@RequestMapping("/memosviacategories")
public class MemoCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private MemoRepository memoRepository; 
	
	@Autowired
	private MemoCategoryRepository mcRepository;
	
	
	//Näytä kaikki "yhdistelmät" http://localhost:8080/memosviacategories/memos
	@RequestMapping(value = {"/memos"})
	public String showAllMemosViaCategories(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		model.addAttribute("memosviacategories", mcRepository.findAll());
		
		return "memos.html";
	}
	

	//Liitetään memo ja kategoria toisiinsa //http://localhost:8080/memosviacategories/add
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/add")
	public String showForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		model.addAttribute("memoandcategory", new MemoCategory());
		model.addAttribute("memos", memoRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		
		return "addMemoWithCategory";
	}
	
	//tallennetaan
	@RequestMapping(value="/saveMemoWithCategory", method = RequestMethod.POST) 
	public String saveMemo(@ModelAttribute("memoandcategory") MemoCategory memoandcategory) {

		mcRepository.save(memoandcategory);
		
		System.out.println("--- TALLENNETAAN memo&category LIITOSTAULU, id: " + memoandcategory.getMemocategoryid() +" --- MemoCategoryController");
		System.out.println("Memo:" + memoandcategory.getMemo().getMemocontent() + ", kategoria: " + memoandcategory.getCategory().getCategoryname());
		return "redirect:/memosviacategories/memos";
	}
	
	
	//Poista liitos
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteArticle(@PathVariable("id") Long mcId, Model model) {
		
		System.out.println("--- POISTETAAN liitostaulu, id: " + mcId +" --- MemoCategoryController");
		
		mcRepository.deleteById(mcId);
		
		return "redirect:../memos"; 
	}
	
	
	//Muokkaa liitosta 
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value= "/edit/{id}", method = RequestMethod.GET)
	public String editArticle(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		System.out.println("--- Muokataan liitostaulua, id: " + id +" --- MemoCategoryController");
		
		model.addAttribute("memoandcategory", mcRepository.findById(id));
		
		model.addAttribute("memos", memoRepository.findAll()); //kaikki memot
		model.addAttribute("categories", categoryRepository.findAll()); //kaikki kategoriat
		
		return "editMemoWithCategory";
	}

}
