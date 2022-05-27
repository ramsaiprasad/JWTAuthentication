package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Followers;

@Repository
public interface FollowersRepository extends JpaRepository<Followers,Long> {

}
