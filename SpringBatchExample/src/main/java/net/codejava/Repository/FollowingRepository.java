package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Following;

@Repository
public interface FollowingRepository extends JpaRepository<Following,Long> {

}
