package net.codejava.StoredProcedures;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogDao extends JpaRepository<NewUsers,Long> {

 @Query(value = "EXEC getAllBlogs :id,:Title", nativeQuery = true)
	public List<Object[]> getAllBlogs(@Param("id")int id,@Param("Title") String Title);
}


