package hh.harkkas23v04.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	List<Comment> findByArticleId(Long articleId);

	
}


