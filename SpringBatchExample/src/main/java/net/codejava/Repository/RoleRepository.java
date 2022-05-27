package net.codejava.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	

}
