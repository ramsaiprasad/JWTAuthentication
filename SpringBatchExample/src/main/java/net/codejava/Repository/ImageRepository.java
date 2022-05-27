package net.codejava.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.codejava.model.ImageProfile;

@Repository
public interface ImageRepository extends JpaRepository<ImageProfile,Long>{
	
	public List<ImageProfile> findByUserId(Long id);

}
