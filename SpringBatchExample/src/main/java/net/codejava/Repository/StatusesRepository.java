package net.codejava.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.codejava.model.Statuses;
import net.codejava.service.UserService;

@Repository
public interface StatusesRepository extends JpaRepository<Statuses,Long> {
	
	 @Query("SELECT S FROM Statuses S WHERE S.uploadedTime<1")
	 List<Statuses> findByUploadedTime(Date date);
	// @Query("SELECT S FROM Statuses S WHERE S.useridd=?1")
	//List<Statuses> findByUseridd(Long id);
	List<Statuses> findAllById(int indexOf);
	
	List<Statuses> findByUseridd(Long id);
	
	
	
	
	 
	

	
}
