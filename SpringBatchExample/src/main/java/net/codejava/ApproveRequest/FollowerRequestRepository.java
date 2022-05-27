package net.codejava.ApproveRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRequestRepository extends JpaRepository<FollowerRequest,Long> {

}
