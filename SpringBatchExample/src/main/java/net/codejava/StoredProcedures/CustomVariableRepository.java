package net.codejava.StoredProcedures;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomVariableRepository extends JpaRepository<CustomVariables,Long> {

}
