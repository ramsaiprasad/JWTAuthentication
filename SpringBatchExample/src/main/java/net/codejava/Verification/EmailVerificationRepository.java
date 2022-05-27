package net.codejava.Verification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Long> {
	
	@Query("SELECT J FROM EmailVerification J WHERE J.token=?1")
	public EmailVerification findByToken(String Token);
	
	@Query("SELECT J FROM EmailVerification J WHERE J.Otp=?1")
	public EmailVerification findByOtp(int Otp);
	
	

}
