package net.codejava.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.AccountLock;
import net.codejava.model.JUser;

@Repository
public interface AccountLockRepository extends JpaRepository<AccountLock, Long> {
	
	Optional<AccountLock> findByUsername(String username);

}
