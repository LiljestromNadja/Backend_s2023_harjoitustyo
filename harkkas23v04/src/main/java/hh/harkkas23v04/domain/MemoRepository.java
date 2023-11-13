package hh.harkkas23v04.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MemoRepository extends CrudRepository<Memo, Long> {

	
	List<Memo> findByMemoid(Long memoid);
	

}
