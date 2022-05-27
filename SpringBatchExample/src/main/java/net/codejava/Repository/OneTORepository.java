package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.OneTO;

@Repository
public interface OneTORepository extends JpaRepository<OneTO,Long>{

}
