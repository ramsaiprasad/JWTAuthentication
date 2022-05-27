package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
