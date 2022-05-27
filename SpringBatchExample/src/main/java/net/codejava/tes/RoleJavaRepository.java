package net.codejava.tes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.RoleJava;

@Repository
public interface RoleJavaRepository extends JpaRepository<RoleJava,Long>{

}
