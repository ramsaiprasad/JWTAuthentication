package net.codejava.tes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Testjav;

@Repository
public interface TestJavRepository extends JpaRepository<Testjav,Long> {

	Optional<Testjav> findByEmail(String email);

}
