package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.ProductExtend;

@Repository
public interface ProductExtendRepository extends JpaRepository<ProductExtend,Long>{

}
