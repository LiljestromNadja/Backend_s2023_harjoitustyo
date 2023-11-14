package hh.harkkas23v06;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import hh.harkkas23v06.domain.Adtype;
import hh.harkkas23v06.domain.AdtypeRepository;
import hh.harkkas23v06.domain.Article;
import hh.harkkas23v06.domain.ArticleRepository;
import hh.harkkas23v06.domain.Comment;
import hh.harkkas23v06.domain.CommentRepository;
import hh.harkkas23v06.domain.Memo;
import hh.harkkas23v06.domain.MemoRepository;

/* ******************************************************************************************************
 * HUOMHUOM! Pakettien erilaiset nimet src/main/java ja src/test/java saattavat aiheuttaa erroreita. 
Esim. jos on nimetty vaikka (tai kopioitu eri projekteista) 
src/main/java/hh.bookstore04 ja src/test/java/k23.bookstore02 == JUnit-testaus ei löydä testiluokkaa. 
*********************************************************************************************************/ 

@SpringBootTest
//@DataJpaTest //h2
public class ArticleRepositoryTests {
	
	
	@Autowired
	ArticleRepository articlerepository;
	
	@Autowired
	AdtypeRepository adtyperepository;
	
	@Autowired
	MemoRepository memoRepository; 
	
	@Autowired
	CommentRepository commentRepository;
	
	String sTime = " " + LocalDateTime.now();
	
	
	
	//Find by title "Piano"
	@Test
	public void findArticleByTitle() {
		System.out.println("JUNIT --- RUNNING TEST --- findArticleByTitle ---" + sTime);
		List<Article> articles = articlerepository.findByTitle("Piano");
		assertThat(articles.get(0).getTitle().equalsIgnoreCase("piano"));
		
	}
	

	//Find by title "Lastenvaatteita"
	@Test
	public void findArticleByAnotherTitle() {
		System.out.println("JUNIT --- RUNNING TEST --- findArticleByAnotherTitle --- " + sTime);
		List<Article> articles = articlerepository.findByTitle("Lastenvaatteita");
		assertThat(articles.get(0).getTitle().equalsIgnoreCase("lastenvaatteita"));
		
	}
	
	
	//Find adtype by name "Helsinki ostetaan"
	@Test
	public void findAdtype() {
		System.out.println("JUNIT --- RUNNING TEST --- findAdtype by name --- " + sTime);
		List<Adtype> adtypes = adtyperepository.findByName("Helsinki ostetaan");
		assertThat(adtypes.get(0).getName().equalsIgnoreCase("helsinki ostetaan"));
		
	}
	
	//Find article by id 1 
	@Test
	public void findByArticleId() {
		
		System.out.println("JUNIT --- RUNNING TEST --- findByArticleId --- " + sTime);
		articlerepository.findById((long)1); 
		List<Article> articles = articlerepository.findById(1); //etsitään id:llä 1
		
		assertThat(articles).hasSize(1); //tuloksen pitäisi olla 1
	}
	
	//Find article by id 1 and edit
	@Test
	public void editArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- editArticle title --- " + sTime);
		
		List<Article> article = articlerepository.findById((long) 1);
		
		System.out.println(article);
		
		article.get(0).setTitle("testi");
		System.out.println("TESTING EDIT ARTICLE: " + article);
		
		System.out.println(article);
		assertThat(article.get(0).getTitle().equalsIgnoreCase("testi"));
		
	}

    
	
	//Find article by id 2 and edit
	@Test
	public void editMemo() {
		
		System.out.println("JUNIT --- RUNNING TEST --- editMemo--- " + sTime);
		
		List<Memo> memo = memoRepository.findByMemoid((long) 2);
		
		//assertNotEquals(memo.get().getId(), null);
		memo.get(0).setMemocontent("Junit testing, editing memo"); //listasta ensimmäinen indeksi
		System.out.println("TESTING EDIT MEMO id 2 : " + memo);
		
		assertThat(memo.get(0).getMemocontent().equalsIgnoreCase("junit testing, editing memo"));
		
			
	}
	
	
	//Create article
	@Test
	public void createArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- createArticle --- " + sTime);
		
		Article article = new Article();
		
		article.setTitle("Junittia!");
		article.setPublisher("JUNIT");
		article.setDescription("creating new article, test");
		
		articlerepository.save(article);
		
		assertNotEquals(article.getId(), 0);
		
		System.out.println("JUNIT --- createArticle ----ARTICLEID: " + article.getId());
		
	}

	//Create comment
	@Test
	public void createComment() {
		
		System.out.println("JUNIT --- RUNNING TEST --- createComment --- " + sTime);
		
		Comment comment = new Comment();
		
		comment.setCommentmessage("Junit");
		
		
		commentRepository.save(comment);
		
		//assertNotEquals(article.getId(), 0);
		assertNotEquals(comment.getCommentid(), 6); //comment id 6
		System.out.println("JUNIT --- createComment --- COMMENTID: " + comment.getCommentid() + ", commentmessage: " + comment.getCommentmessage());
		
	}
	
	
	
	//Create article with id 4 and find article by id 4 
	@Test
	public void createaAnotherArticle() {
		
		System.out.println("JUNIT --- RUNNING TEST --- createaAnotherArticle --- " + sTime);
		
		Article article = new Article();
		
		article.setTitle("Testing articlerepository");
		article.setPublisher("JUNIT");
		article.setDescription("creating new article, test");
		
		articlerepository.save(article);
		System.out.println("JUNIT --- createArticle ----ARTICLEID: " + article.getId());
		
		//articlerepository.findById((long)11); 
		//List<Article> articles = articlerepository.findById(13);
		
		assertEquals(article.getId(), 15);
		
		
		

		

	}
	
	


    


}
