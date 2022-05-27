package net.codejava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AuthenticationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;

import net.codejava.Repository.JUserRepository;
import net.codejava.Repository.RoleRepository;
import net.codejava.Repository.UserRepository;
import net.codejava.Security.UserDetailsImpl;
import net.codejava.StoredProcedures.CustomVariableRepository;
import net.codejava.StoredProcedures.CustomVariables;
import net.codejava.StoredProcedures.FieldValues;
import net.codejava.StoredProcedures.FieldValuesRepository;
import net.codejava.model.JUser;
import net.codejava.model.Role;
import net.codejava.model.User;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
class SpringBatchExampleApplicationTests {

	@Autowired
	private UserRepository Urepo;
	@Autowired
	private JUserRepository JU;
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private RoleRepository Rrepo;
	@Autowired
	private CustomVariableRepository CVR;
	@Autowired
	private FieldValuesRepository FVR;
	@Test
	@Rollback(false)
	void test() {
		
		Role roleAdmin=new Role("Admin");
		Role roleUser=new Role("User");
		Role roleEditor=new Role("Editor");
		
		entityManager.persist(roleAdmin);
		entityManager.persist(roleUser);
		entityManager.persist(roleEditor);
		
		
	}
	
	@Test 
   @Rollback(false)
	void createUserWithOneRole()
	{
		Long a=(long) 1;
		Role roleAdmin=entityManager.find(Role.class, a);
		
		User user=new User();
		user.setEmail("ramsai@gmail");
		user.setPassword("ramsai");
		user.addRole(roleAdmin);
		
		Urepo.save(user);
	}
	@Test 
	   @Rollback(false)
		void createUserWithTwoRole()
		{
			Long a=(long) 1;
			Long b=(long) 2;
			Role roleAdmin=entityManager.find(Role.class, a);
			Role roleEditor=entityManager.find(Role.class, b);
			
			User user=new User();
			user.setEmail("ramsai@gmail");
			user.setPassword("ramsai");
			user.addRole(roleAdmin);
			user.addRole(roleEditor);
			
			Urepo.save(user);
		}

	 @Test
	 @Rollback(false)
	 void addRoleToExisiting()
	 {
		 Long d=(long)1;
		 User user=Urepo.findById(d).get();
		 
		 Role roleUser=entityManager.find(Role.class, (long)4);
		 
		 user.addRole(roleUser);
		 User ad=Urepo.getById(d);
		 System.out.println(ad);
	 }
	 
	
	 @Test
	 @Rollback(false)
	 void testRoleWithNewUser()
	 {
		 Role roleSupervisor=new Role("Supervisor");
		 User user=new User();
		 user.setEmail("test@gmail.com");
		user.setPassword("Password");
		 user.addRole(roleSupervisor);
		 Urepo.save(user);
	 }
	 @Test
	 @Rollback(false)
	 void testRoleWithNewUsertest()
	 {
		 Role roleSupervisor=new Role("Tranier");
		 User user=new User();
		 user.setEmail("test1@gmail.com");
		user.setPassword("Password");
		 user.addRole(roleSupervisor);
		 Urepo.save(user);
	 }
	 @Test
	 @Rollback(false)
	 void getRole()
	 {
		 User user=Urepo.findById((long)8).get();
		 entityManager.detach(user);
		 System.out.println(user.getEmail());
		 //System.out.println(user.getRoles());
	 }
	
	 @Test
	 
	 void findAll()
	 {
		 List<User> list=Urepo.findAll();
		 System.out.println(list);
	 }
	 @Test
	 void getAll()
	 {
		 List<Role> list=Rrepo.findAll();
		 System.out.println(list);
	 }
	 @Test
	 @Rollback(false)
	 void CreateCustomField() {
		 
		 CustomVariables custom=new CustomVariables();
		 List<String> names=new ArrayList<>();
		 names.add("AadharNumber");
		 names.add("PhoneNumber");
		 names.add("gender");
		 List<CustomVariables> cust=new ArrayList<>();
		 for (int i=0;i<=names.size()-1;i++) {
		 
		   custom.setCustom_Variable(names.get(i));
		   cust.add(custom);
		    
		  }
		 CVR.saveAll(cust);
		 
	 }
	 @Test
	 @Rollback(false)
	 void CreateValueForCustomVariable()
	 {
		 FieldValues FeldValue=new FieldValues();
		 FeldValue.setField_Values("7845");
		 CustomVariables custom=CVR.getById((long) 1);
		 FeldValue.setCustomVariables(custom);
		 FVR.save(FeldValue);
		 
	 }
	 @Test
	 @Rollback(false)
	 void AssignValueForUser()
	 
	 {
		 FieldValues FeldValue=new FieldValues();
		 
		 FeldValue.setField_Values("78459");
		 CustomVariables custom=CVR.getById((long) 1);
		 JUser user=JU.getById((long)1);
		 FeldValue.setCustomVariables(custom);
		 FeldValue.setUser(user);
		 FVR.save(FeldValue);
	 }
}
