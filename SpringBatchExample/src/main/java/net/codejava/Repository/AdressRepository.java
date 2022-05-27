package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress,Long> {

}
