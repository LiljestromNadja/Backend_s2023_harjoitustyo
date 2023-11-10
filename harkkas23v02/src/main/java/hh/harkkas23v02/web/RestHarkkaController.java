package hh.harkkas23v02.web;

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

import hh.harkkas23v02.domain.Article;
import hh.harkkas23v02.domain.ArticleRepository;
import hh.harkkas23v02.domain.Comment;
import hh.harkkas23v02.domain.CommentRepository;

@RestController //palauttaa JSONia
//polku voidaan määrittää myös tässä: @RequestMapping("/api/articlestore")
public class RestHarkkaController {
	
	
	//Injektoidaan ArticleRepository RestHarkkaController-luokalle
	@Autowired
	ArticleRepository articleRepository;
	
	//Injektoidaan CommentRepostitory RestHarkkaController -luokalle
	@Autowired
	CommentRepository commentRepository;
	

	
	
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
	
	
	
	
	
	
	

	
	


}
