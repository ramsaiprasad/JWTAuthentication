package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long>{

}
