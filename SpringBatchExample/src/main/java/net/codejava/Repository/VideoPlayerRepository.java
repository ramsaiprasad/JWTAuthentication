package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.VideoPlayer;

@Repository
public interface VideoPlayerRepository extends JpaRepository<VideoPlayer,Long> {

}
