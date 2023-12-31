package hh.harkkas23v01.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

	List<Article> findByDescription(String description); 
	List<Article> findByTitle(String title); 
	List<Article> findById(long id);
	List<Article> deleteById(long id);
	List<Article> findByPublisher(String publisher);
	
	
	
	//Optional<Article> findByArticleId(long id);
	

	//voi tehdä tietokantaan hakuja eri attribuuteilla, esim findById(Long id), findByPublisher(String publisher)
}
