package net.codejava.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import net.codejava.model.JUser;

@Repository
public interface JUserRepository extends JpaRepository<JUser,Long> {

	 // @Query("select new JUser(j.username ,j.email,j.coverPic,j.profilePic) from JUser j where j.username= ?1")
	Optional<JUser> findByUsername(String username);
	

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	//@Query("SELECT new JUser(u.id,u.username,u.profilephoto,u.posts,u.statu) from JUser u")
	 //List<JUser> findAllActiveUser();
	
	//@Query("SELECT new JUser(d.id,d.username,d.email,d.profilePic,d.coverPic) from JUser d   WHERE d.status=1")
	 //List<JUser> findAllActiveUsers();
	@Query("SELECT J FROM JUser J WHERE J.resetPasswordToke=?1")
	public JUser findByResetPasswordToken(String resetPasswordToke);
	
	@Query("SELECT J FROM JUser J WHERE J.email=?1")
	public JUser findByEmail(String email);
	
	@Query("update JUser J SET J.failed_attempts = ?1 where J.username=?2")
	@Modifying
	public void updateFailedAttempt(Integer failed_attempts,String username);
	
	
	
	
	
	

}
