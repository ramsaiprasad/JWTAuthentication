package net.codejava.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.CoverProfile;
import net.codejava.model.JUser;
import net.codejava.model.ProfilePhoto;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto,Long>{

	public List<ProfilePhoto> findByUserId(Long id);

	
}
