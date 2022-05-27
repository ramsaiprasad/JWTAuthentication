package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.FollowerLists.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long>{

}
