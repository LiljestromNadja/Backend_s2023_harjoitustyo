package hh.harkkas23v06.web;

/***
 * Muista apien salliminen/kieltäminen/rajoittaminen 
 * WebSecurityConfig.java
 * 
 * **/

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.harkkas23v06.domain.Adtype;
import hh.harkkas23v06.domain.AdtypeRepository;
import hh.harkkas23v06.domain.ApplicationUser;
import hh.harkkas23v06.domain.ApplicationUserRepository;
import hh.harkkas23v06.domain.Article;
import hh.harkkas23v06.domain.ArticleRepository;
import hh.harkkas23v06.domain.Comment;
import hh.harkkas23v06.domain.CommentRepository;
import hh.harkkas23v06.domain.Memo;
import hh.harkkas23v06.domain.MemoRepository;

@RestController //palauttaa JSONia
//polku voidaan määrittää myös tässä: @RequestMapping("/api/articlestore")
public class RestHarkkaController {
	
	
	//Injektoidaan ArticleRepository RestHarkkaController-luokalle
	@Autowired
	ArticleRepository articleRepository;
	
	//Injektoidaan CommentRepostitory RestHarkkaController -luokalle
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ApplicationUserRepository appUserRepository;

	@Autowired
	AdtypeRepository adtypeRepository;
	
	@Autowired
	MemoRepository memoRepository;
	
	
	//Articles
	
	
	//Palautetaan lista myyntiartikkeleista http://localhost:8080/articlesjson
	@GetMapping("/articlesjson")
	public Iterable<Article> getArticles() {
		System.out.println("--- NOUDA JA PALAUTA LISTA TUOTTEISTA --- RestHarkkaController");
		return articleRepository.findAll();
	}
	
	//Lisätään uusi myytävä tuote 
	@PostMapping("articlesjson")
	Article newArticle(@RequestBody Article newArticle) {
		System.out.println("--- LISÄTÄÄN MYYTÄVÄ TUOTE "+ newArticle + " --- RestHarkkaController");
		return articleRepository.save(newArticle);
	}
	
	//Muokataan olemassa olevaa myytävää tuotetta
	@PutMapping("/articlesjson/{id}")
	Article editArticle(@RequestBody Article editedArticle, @PathVariable Long id) {
		System.out.println("--- MUOKATAAN MYYTÄVÄÄ TUOTETTA "+ editedArticle + " --- RestHarkkaController");
		editedArticle.setId(id);
		return articleRepository.save(editedArticle);
	}
	
	/*//delete article
	@DeleteMapping("/articlesjson/{id}")
	void deleteArticle(@PathVariable Long id) {
		articleRepository.deleteById(id);
	}*/
	
	//Poistetaan myytävä tuote
	@DeleteMapping("/articlesjson/{id}")
	public Iterable<Article> deleteArticle(@PathVariable Long id) {
		System.out.println("--- POISTETAAN TUOTE, id: " + id + " --- RestHarkkaController");
		articleRepository.deleteById(id);
		return articleRepository.findAll();
	}
	
	//Etsitään ja palautetaan yksi tuote id:n perusteella 
	@GetMapping("/articlesjson/{id}")
	Optional<Article> getArticle(@PathVariable Long id) {
		System.out.println("--- ETSITÄÄN TUOTE, id: " + id + " --- RestHarkkaController");
		return articleRepository.findById(id);
		
	}
	
	


	
	//Comments
	
	
	//Palautetaan lista kommenteista GET http://localhost:8080/commentsjson
	@GetMapping("/commentsjson")
	public Iterable<Comment> getComments() {
		System.out.println("--- KAIKKI KOMMENTIT --- RestHarkkaController");
		return commentRepository.findAll();
	}
	
	
	//Palautetaan yksi kommentti id:n perusteella GET http://localhost:8080/commentsjson/1
	@GetMapping("/commentsjson/{id}")
	Optional<Comment> getComment(@PathVariable Long id) {
		System.out.println("--- PALAUTETAAN KOMMENTTI, JONKA ID: " + id + " --- RestHarkkaController");
		return commentRepository.findById(id);
		
	}
	
	//Lisätään uusi kommentti POST http://localhost:8080/commentsjson	
	@PostMapping("/commentsjson")
	Comment newComment(@RequestBody Comment newComment) {
		System.out.println("--- LISÄTÄÄN KOMMENTTI " + newComment + " --- RestHarkkaController");
		return commentRepository.save(newComment);
	}
	
	// Muokataan kommenttia id:n perusteella PUT http://localhost:8080/commentsjson/1
	@PutMapping("/commentsjson/{id}")
	Comment editComment(@RequestBody Comment editedComment, @PathVariable Long id) {
		System.out.println("--- MUOKATAAN KOMMENTTIA, JONKA ID: " + id + " --- RestHarkkaController");
		editedComment.setCommentid(id);
		return commentRepository.save(editedComment);
	}
	
	
	// Poistetaan kommentti DELETE http://localhost:8080/commentsjson/2
	@DeleteMapping("/commentsjson/{id}")
	public Iterable<Comment> deleteComment(@PathVariable Long id) {
		
		System.out.println("--- POISTETAAN KOMMENTTI, JONKA ID: " + id + " --- RestHarkkaController");
		commentRepository.deleteById(id);
		return commentRepository.findAll();
	}
	
	
	
	
	
	//Application Users (muista laittaa White_List_URLs)
	
	//ApplicationUser-entiteetissä määritetty password_hash IgnoreJson
	
	//Palautetaan lista käyttäjistä GET http://localhost:8080/appusersjson
	@GetMapping("/appusersjson")
	public Iterable<ApplicationUser> getApplicationUsers() {
		System.out.println("--- KAIKKI KÄYTTÄJÄT --- RestHarkkaController");
		return appUserRepository.findAll();
	}
	
	
	//Palautetaan yksi käyttäjä id:n perusteella GET http://localhost:8080/appusersjson/1
	@GetMapping("/appusersjson/{id}")
	Optional<ApplicationUser> getApplicationUser(@PathVariable Long id) {
		System.out.println("--- PALAUTETAAN KÄYTTÄJÄ, JONKA ID: " + id + " --- RestHarkkaController");
		return appUserRepository.findById(id);
		
	}
	
	// Poistetaan käyttäjä DELETE http://localhost:8080/appusersjson/1
	@DeleteMapping("/appusersjson/{id}")
	public Iterable<ApplicationUser> deleteApplicationUser(@PathVariable Long id) {
		
		System.out.println("--- POISTETAAN KÄYTTÄJÄ, JONKA ID: " + id + " --- RestHarkkaController");
		appUserRepository.deleteById(id);
		return appUserRepository.findAll();
	}
	
	
	
	
	
	
	
	//AdType
	
	//Listataan ilmoitustyypit GET http://localhost:8080/adtypesjson
	@GetMapping("/adtypesjson")
	public Iterable<Adtype> getAdtypes() {
		System.out.println("--- KAIKKI ILMOITUSTYYPIT --- RestHarkkaController");
		return adtypeRepository.findAll();
	}
	
	//Lisätään uusi ilmoitustyyppi POST http://localhost:8080/adtypesjson	
	@PostMapping("/adtypesjson")
	Adtype newAdtype(@RequestBody Adtype newAdtype) {
		System.out.println("--- LISÄTÄÄN ILMOITUSTYYPPI " + newAdtype + " --- RestHarkkaController");
		return adtypeRepository.save(newAdtype);
	}
	
	
	// Muokataan ilmoitustyyppiä id:n perusteella PUT http://localhost:8080/adtypesjson/1
	@PutMapping("/adtypesjson/{id}")
	Adtype editAdtype (@RequestBody Adtype editedAdtype , @PathVariable Long id) {
		System.out.println("--- MUOKATAAN ILMOITUSTYYPPIÄ, JONKA ID: " + id + " --- RestHarkkaController");
		editedAdtype.setAdtypeid(id);
		return adtypeRepository.save(editedAdtype);
	}
		
	
	// Poistetaan ilmoitustyyppi DELETE http://localhost:8080/adtypesjson/1
	@DeleteMapping("/adtypesjson/{id}")
	public Iterable<Adtype> deleteAdtype(@PathVariable Long id) {
		
		System.out.println("--- POISTETAAN ILMOITUSTYYPPI, JONKA ID: " + id + " --- RestHarkkaController");
		adtypeRepository.deleteById(id);
		return adtypeRepository.findAll();
		
		
	}
	
	
	
	
	//Memo
	
	
	//Haetaan kaikki memot GET http://localhost:8080/memosjson
	@GetMapping("/memosjson")
	public Iterable<Memo> getMemos() {
		System.out.println("--- KAIKKI MEMOT --- RestHarkkaController");
		return memoRepository.findAll();
	}
	
	
	//Etsitään ja palautetaan memo id:n perusteella  GET http://localhost:8080/memosjson/2
	@GetMapping("/memosjson/{id}")
	Optional<Memo> getMemo(@PathVariable Long id) {
		System.out.println("--- ETSITÄÄN MEMO, id: " + id + " --- RestHarkkaController");
		return memoRepository.findById(id);
		
	}

	
	//Lisätään uusi memo POST http://localhost:8080/memosjson	
	@PostMapping("/memosjson")
	Memo newMemo(@RequestBody Memo newMemo) {
		System.out.println("--- LISÄTÄÄN MEMO " + newMemo + " --- RestHarkkaController");
		return memoRepository.save(newMemo);
	}
	
	//Poistetaan memo DELETE http://localhost:8080/memosjson/2
	@DeleteMapping("/memosjson/{id}")
	public Iterable<Memo> deleteMemo(@PathVariable Long id) {
		System.out.println("--- POISTETAAN MEMO, id: " + id + " --- RestHarkkaController");
		memoRepository.deleteById(id);
		return memoRepository.findAll();
	}
	
	//Muokataan olemassa olevaa memoa id:perusteella
	@PutMapping("/memosjson/{id}")
	Memo editMemo(@RequestBody Memo editedMemo, @PathVariable Long id) {
		System.out.println("--- MUOKATAAN MUISTIINPANOA "+ editedMemo + " --- RestHarkkaController");
		editedMemo.setMemoid(id);
		return memoRepository.save(editedMemo);
	}
	
	
	

	
	


}
