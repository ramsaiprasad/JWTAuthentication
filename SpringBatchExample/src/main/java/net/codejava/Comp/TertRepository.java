package net.codejava.Comp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TertRepository extends JpaRepository<Tert,Long> {

}
