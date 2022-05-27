package net.codejava.SecurityToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenSecurityRepository extends JpaRepository<TokenSecurity,Long> {

}
