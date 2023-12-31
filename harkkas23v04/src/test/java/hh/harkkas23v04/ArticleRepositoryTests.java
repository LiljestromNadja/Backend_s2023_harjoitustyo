package hh.harkkas23v04;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.harkkas23v04.domain.AdtypeRepository;
import hh.harkkas23v04.domain.Article;
import hh.harkkas23v04.domain.ArticleRepository;

/* ******************************************************************************************************
 * HUOMHUOM! Pakettien erilaiset nimet src/main/java ja src/test/java saattavat aiheuttaa erroreita. 
Esim. jos on nimetty vaikka (tai kopioitu eri projekteista) 
src/main/java/hh.bookstore04 ja src/test/java/k23.bookstore02 == JUnit-testaus ei löydä testiluokkaa. 
*********************************************************************************************************/ 


@DataJpaTest
public class ArticleRepositoryTests {
	
	
	@Autowired
	ArticleRepository articlerepository;
	
	@Autowired
	AdtypeRepository adtyperepository;
	
	String sTime = " " + LocalDateTime.now();
	
	//Find by title "Ohjelmoinnin salat"
	@Test
	public void findArticleByTitle() {
		System.out.println("JUNIT --- RUNNING TEST --- findArticleByTitle ---" + sTime);
		List<Article> articles = articlerepository.findByTitle("Ohjelmoinnin salat");
		assertThat(articles.get(0).getTitle().equalsIgnoreCase("ohjelmoinnin salat"));
		
	}
	
	//Find by title "Tietokannat"
	@Test
	public void findArticleByAnotherTitle() {
		System.out.println("JUNIT --- RUNNING TEST --- findArticleByAnotherTitle --- " + sTime);
		List<Article> books = articlerepository.findByTitle("Tietokannat");
		assertThat(books.get(0).getTitle().equalsIgnoreCase("tietokannat"));
		
	}
	
	//Find by id 1 
	@Test
	public void findByArticleId() {
		
		System.out.println("JUNIT --- RUNNING TEST --- findByArticleId --- " + sTime);
		articlerepository.findById((long)1); 
		List<Article> articles = articlerepository.findById(1); //etsitään id:llä 1
		
		assertThat(articles).hasSize(1); //tuloksen pitäisi olla 1
	}
	
	//Find by id 1 and edit
	@Test
	public void editArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- editArticle --- " + sTime);
		
		List<Article> article = articlerepository.findById((long) 1);
		//assertNotEquals(article.get().getId(), null);
		article.get(0).setPublisher("testi");
		System.out.println("TESTING EDIT ARTICLE: " + article);
		List<Article> articles = articlerepository.findByPublisher("testi");
		assertThat(articles).hasSize(1);
	}
	
	//Find by id 2 and edit
	@Test
	public void editArticleId2() {
		
		System.out.println("JUNIT --- RUNNING TEST --- editArticleId2 --- " + sTime);
		
		List<Article> article = articlerepository.findById((long) 2);
		//assertNotEquals(article.get().getId(), null);
		article.get(0).setTitle("testi"); //listasta ensimmäinen indeksi
		System.out.println("TESTING EDIT ARTICLE id 2 : " + article);
		List<Article> articles = articlerepository.findByTitle("testi");
		assertThat(articles).hasSize(1);
	}
	
	//Create article
	@Test
	public void createArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- createArticle --- " + sTime);
		
		Article article = new Article();
		articlerepository.save(article);
		
		assertNotEquals(article.getId(), 0);
		
		System.out.println("JUNIT --- createArticle ----ARTICLEID: " + article.getId());
		
	}

	//Article: id, String title, String title, String publisher, String dateadded, double price
	
	//Create another article 
	@Test
	public void createAnotherArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- createAnotherArticle --- " + sTime);
		
		Article article = new Article("Junit test article", "Lots of Junit stuff" ,"JUNIT", "2023-09-11", 12,34);
		articlerepository.save(article);
		
		//assertNotEquals(article.getId(), 0);
		assertEquals(article.getId(), 5); //article id 5
		System.out.println("JUNIT --- createAnotherArticle ----ARTICLEID: " + article.getId() + ", title: " + article.getTitle());
		
	}
	
	//Create article with id 4 and find article by id 4 
	@Test
	public void createaAnotherArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- createaAnotherArticle --- " + sTime);
		
		Article article = new Article();
		articlerepository.save(article);
		System.out.println("JUNIT --- createArticle ----ARTICLEID: " + article.getId());
		
		articlerepository.findById((long)4); 
		List<Article> articles = articlerepository.findById(1);
		
		assertThat(articles).hasSize(1); //tuloksen pitäisi olla 1
		
		

		

	}
	

	//Delete book 
	@Test
    public void deleteNewArticle() {
    	System.out.println("JUNIT --- RUNNING TEST --- deleteNewArticle --- " + sTime);
		List<Article> books = articlerepository.findByTitle("Tietokannat");
		Article book = books.get(0);
		articlerepository.delete(book);
		List<Article> newArticles = articlerepository.findByTitle("Tietokannat");
		assertThat(newArticles).hasSize(0);
     }
	

	//get all books
    @Test
    public void findAllArticles() {
    	System.out.println("JUNIT --- RUNNING TEST --- findAllArticles --- " + sTime);
    	Iterable<Article> books = articlerepository.findAll(); //haetaan kaikki kirjat
    	assertThat(books).hasSize(3);// listan pituus pitäisi olla 3
    }
    

    

    


    


}
