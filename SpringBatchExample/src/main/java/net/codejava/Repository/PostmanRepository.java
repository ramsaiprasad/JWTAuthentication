package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Postman;

@Repository
public interface PostmanRepository extends JpaRepository<Postman,Long> {

}
