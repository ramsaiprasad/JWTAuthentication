package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.remo;

@Repository
public interface RemoRepository extends JpaRepository<remo,Long> {

}
