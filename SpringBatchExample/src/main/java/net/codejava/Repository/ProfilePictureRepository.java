package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.ProfilePicture;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture,Long> {

}
