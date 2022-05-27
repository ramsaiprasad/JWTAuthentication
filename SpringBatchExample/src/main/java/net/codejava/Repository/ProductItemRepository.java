package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Product;

@Repository
public interface ProductItemRepository extends JpaRepository<Product,Long> {

}
