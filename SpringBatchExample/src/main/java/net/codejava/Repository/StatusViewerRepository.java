package net.codejava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.model.Views.StatusViews;

@Repository
public interface StatusViewerRepository extends JpaRepository<StatusViews,Long>{

}
