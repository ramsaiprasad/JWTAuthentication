package net.codejava.DBOperations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmetnRepository extends JpaRepository<Department,Long>{

 public  Department findByDepartmentname(String name);
}
