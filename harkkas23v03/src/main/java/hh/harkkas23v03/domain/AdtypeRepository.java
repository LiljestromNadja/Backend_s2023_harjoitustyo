package hh.harkkas23v03.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdtypeRepository extends CrudRepository<Adtype, Long> {

    List<Adtype> findByName(String name);
    
}