package hh.harkkas23v05.web;

import java.util.List;

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
import hh.harkkas23v05.domain.Article;
import hh.harkkas23v05.domain.ArticleRepository;
import hh.harkkas23v05.domain.Comment;
import hh.harkkas23v05.domain.CommentRepository;
import jakarta.validation.Valid;

@Controller
public class CommentController {
	
	@Autowired 
	private CommentRepository commentrepository;
	
	@Autowired
	private ArticleRepository articlerepository;
	
	@Autowired
	private ApplicationUserRepository applicationuserrepository;
	
	//Kommenttien listaus
	
	//Listataan KAIKKI kommentit KAIKKIIN tuotteisiin
	//USER ja ADMIN
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value = {"/allComments"}) //endpoint:  http://localhost:8080/listComments
	public String listComments(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		System.out.println("---NÄYTETÄÄN KAIKKI KOMMENTIT --- CommentController.java ");

		model.addAttribute("comments", commentrepository.findAll());
			
		return "allComments.html";	
	}


	//Listaa YHDEN tuotteen kommentit tuotteen id:n perusteella
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus poistaa
	@RequestMapping(value="/article/{id}/comments", method=RequestMethod.GET)
	public String showCommentsForArticle(@PathVariable("id") Long articleId, Model model) {
		
		//Hae kommentit tuotteen id:n perusteella
		List<Comment> articleComments = commentrepository.findByArticleId(articleId);
		
		System.out.println("--- NÄYTETÄÄN KAIKKI KOMMENTIT TUOTTEELLE JONKA ID: " + articleId + "---CommentContoller");
		//System.out.println(articleComments);
		
		model.addAttribute("comments", articleComments);
		
		return "articleComments";
	}
	
	

	//Kommenttien lisääminen tuotteen id:n perusteella
	
	//Lisätään kommentti
	//Tänne ohjataan articlelist -sivulta "Lisää kommentti"
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="addComment/{id}") //tässä siis id on kommentoitavan tuotteen id 
	public String addComment(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
	
		Comment comment = new Comment();
		
		comment.setArticle(articlerepository.findById(id).get());
		
		System.out.println("");
		System.out.println("--- MYYTÄVÄ TUOTE JOTA KOMMENTOIDAAN" + comment.getArticle() + "--- CommentController, addComment");
		System.out.println("KOMMENTTI: " + comment.getCommentid());

		model.addAttribute("comment", comment);
		
		System.out.println("KOMMENTTI, articleID: " + articlerepository.findById(id).get());
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername()));
		
		return "addComment";
	}
	
	//Tallennetaan kommentti
	//ADMIN ja USER
	//Tänne ohjataan tallennus seuraavista endpointeista:  addComment/{id}, /article/{id}/addComment/
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/saveComment", method = RequestMethod.POST) 
	public String save(@Valid Comment comment,  Article article, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (bindingResult.hasErrors()) {
			System.out.println("--- Validointivirhe --- CommentController");
			model.addAttribute("comment", comment);
			model.addAttribute("article", articlerepository.findById(comment.getArticle().getId())); 
			model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); 
			return "addComment";
		}
		
		
		commentrepository.save(comment);
		
		System.out.println("--- LISÄTTY KOMMENTTI: " + comment + comment.getArticle() + " --- CommentController, addComment");
		System.out.println("Lisääjän käyttäjätunnus: " + comment.getApplicationuser().getUsername());
		return "redirect:/allComments"; //ohjataan sivulle jossa listattuna kaikki kommentit
	}




	//Lisätään kommentti, esimerkkipolku http://localhost:8080/article/3/addComment/
	//ADMIN ja USER
	//tänne ohjataan YKSITTÄISEN tuotteen kommenttien listaussivulta, esim http://localhost:8080/article/8/comments "Add Comment"
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus lisätä
	@RequestMapping(value="/article/{id}/addComment/")
	public String addCommentByArticleId(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
	
		Comment comment = new Comment(); 
		
		comment.setArticle(articlerepository.findById(id).get());
		
		System.out.println("--- MYYTÄVÄ TUOTE JOTA KOMMENTOIDAAN" + comment.getArticle() + "--- CommentController, addComment");
		System.out.println("KOMMENTTI: " + comment.getCommentid());

		model.addAttribute("comment", comment);
		
		System.out.println("---KOMMENTOITAVAN TUOTTEEN ID: " + articlerepository.findById(id).get() + "--- CommentController, addComment");
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); 
		
		return "addComment"; 
	}

	
	
	//06-11-2023
	//Lisätään kommentti valittuun tuotteeseen, tänne tullaan KAIKKIEN kommenttien listauksesta (Valitse ilmoitus, jota haluat kommentoida). Esimerkkipolku http://localhost:8080/chooseArticleToComment/
	//ADMIN ja USER
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus
	@RequestMapping(value="chooseArticleToComment/")
	public String addComment(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		
		model.addAttribute("comment", new Comment());
		model.addAttribute("articles", articlerepository.findAll()); //listataan tuotteet
		model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); 
		
		return "chooseAndAddComment"; //tässä käytetään eri lomaketta, kuin yhden tuotteen kommentoinneissa id:n perusteella
	}
	
	//06-11-2023
	//Tallennetaan kommentti (Valitse ilmoitus, jota haluat kommentoida)
	//ADMIN ja USER
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')") //metoditason tarkistus onko oikeus 
	@RequestMapping(value="/saveCommentToSelectedArticle", method = RequestMethod.POST) //tämä käytössä chooseAndAddComment.html :ssä
	public String save(@Valid Comment comment,  BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (bindingResult.hasErrors()) {
			System.out.println("Validointivirhe! --- CommentController");
			model.addAttribute("comment", comment);
			model.addAttribute("article", articlerepository.findById(comment.getArticle().getId())); // <---- tämän takia edellinen kaatui, tärkeä
			model.addAttribute("applicationuser", applicationuserrepository.findByUsername(userDetails.getUsername())); 
			return "chooseAndAddComment";
		}
		commentrepository.save(comment);
		System.out.println("LISÄTTY KOMMENTTI: " + comment + comment.getArticle());
		System.out.println("Lisääjän käyttäjätunnus: " + comment.getApplicationuser().getUsername());
		return "redirect:/allComments";
	}
	
	

	//Kommenttien hallinnointi, edit ja delete, vain admin
	
	//31-10-2023
	//Poista kommentti
	//ADMIN
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/deleteComment/{id}", method=RequestMethod.GET)
	public String deleteArticle(@PathVariable("id") Long commentId, Model model) {
		commentrepository.deleteById(commentId);
		System.out.println("--- POISTETAAN KOMMENTTI, id: " + commentId + "--- CommentController");
		return "redirect:../allComments"; 
	}
	
	//06-11-2023 Edit ei toistaiseksi tarpeellinen
	
	
	
	
	
	
	
	

}
