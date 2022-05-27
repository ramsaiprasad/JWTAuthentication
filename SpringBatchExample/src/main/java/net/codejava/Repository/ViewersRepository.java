package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Viewers;

@Repository
public interface ViewersRepository extends JpaRepository<Viewers,Long>{

}
