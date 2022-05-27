package net.codejava.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.CoverProfile;
import net.codejava.model.ImageProfile;

@Repository
public interface CoverProfileRepository extends JpaRepository<CoverProfile,Long>{
	public List<CoverProfile> findByUserId(Long id);
	

}
