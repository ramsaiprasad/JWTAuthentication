package net.codejava.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.ERole;
import net.codejava.model.JRole;

@Repository
public interface JRoleRepository  extends JpaRepository<JRole,Long>{
	
	Optional<JRole> findByName(ERole name);

}
