package net.codejava.Repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Streaming;

@Repository
public interface StreamRepository extends CrudRepository<Streaming,Long>{

}
