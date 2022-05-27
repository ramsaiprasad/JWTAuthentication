package net.codejava.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts,Long> {

}
