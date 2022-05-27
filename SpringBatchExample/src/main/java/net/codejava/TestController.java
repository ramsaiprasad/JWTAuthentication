package net.codejava;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Exceptions.ExceptionMessages;
import io.netty.util.internal.ThreadLocalRandom;
import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;
import net.codejava.ApproveRequest.FollowerRequest;
import net.codejava.ApproveRequest.FollowerRequestRepository;
import net.codejava.ApproveRequest.FollowerRequestService;
import net.codejava.Call.CallableWorker;
import net.codejava.Call.DataManipulation;
import net.codejava.Call.JUserCallable;
import net.codejava.Call.MyCallable;
import net.codejava.Call.Rest;
import net.codejava.Call.SquareCalculator;
import net.codejava.Call.StringCallable;
import net.codejava.Call.TestCallble;
import net.codejava.Call.UserCallService;
import net.codejava.Comp.ExtendedeCal;
import net.codejava.Comp.SvaeCallable;
import net.codejava.Comp.Tert;
import net.codejava.Comp.TertRepository;
import net.codejava.Comp.UserServiceComact;
import net.codejava.Repository.AccountLockRepository;
import net.codejava.Repository.AdressRepository;
import net.codejava.Repository.CoverPhotoRepository;
import net.codejava.Repository.CoverProfileRepository;
import net.codejava.Repository.FollowersRepository;
import net.codejava.Repository.FollowingRepository;
import net.codejava.Repository.ImageRepository;
import net.codejava.Repository.JRoleRepository;
import net.codejava.Repository.JUserRepository;
import net.codejava.Repository.OneTORepository;
import net.codejava.Repository.PerminantAddressRepository;
import net.codejava.Repository.PostRepository;
import net.codejava.Repository.PostmanRepository;
import net.codejava.Repository.ProductExtendRepository;
import net.codejava.Repository.ProductItemRepository;
import net.codejava.Repository.ProfilePhotoRepository;
import net.codejava.Repository.ProfilePictureRepository;
import net.codejava.Repository.RemoRepository;
import net.codejava.Repository.StatusRepository;
import net.codejava.Repository.StatusViewerRepository;
import net.codejava.Repository.StatusesRepository;
import net.codejava.Repository.StoryRepository;
import net.codejava.Repository.UserRepository;
import net.codejava.Repository.VideoPlayerRepository;
import net.codejava.Request.StatusRequest;
import net.codejava.Request.UpdateRequest;
import net.codejava.Response.MessageResponse;
import net.codejava.Security.Utility;
import net.codejava.SecurityToken.TokenSecurity;
import net.codejava.SecurityToken.TokenSecurityRepository;
import net.codejava.StoredProcedures.BlogDao;
import net.codejava.StoredProcedures.BlogService;
import net.codejava.StoredProcedures.CustomVariableRepository;
import net.codejava.StoredProcedures.CustomVariables;
import net.codejava.StoredProcedures.ListOfIndexesProcedure;
import net.codejava.StoredProcedures.NewUsers;
import net.codejava.StoredProcedures.StoredProcedureService;
import net.codejava.StoredProcedures.TableNames;
import net.codejava.StoredProcedures.UserRequest;
import net.codejava.Verification.EmailVerification;
import net.codejava.Verification.EmailVerificationRepository;
import net.codejava.model.AccountLock;
import net.codejava.model.Adress;
import net.codejava.model.CoverPhoto;
import net.codejava.model.CoverProfile;
import net.codejava.model.ERole;
import net.codejava.model.Followers;
import net.codejava.model.Following;
import net.codejava.model.ImageProfile;
import net.codejava.model.JRole;
import net.codejava.model.JUser;
import net.codejava.model.OneTO;
import net.codejava.model.PerminantAddress;
import net.codejava.model.Postman;
import net.codejava.model.Posts;
import net.codejava.model.Product;
import net.codejava.model.ProductExtend;
import net.codejava.model.ProfilePhoto;
import net.codejava.model.ProfilePicture;
import net.codejava.model.Status;
import net.codejava.model.Statuses;
import net.codejava.model.User;
import net.codejava.model.VideoPlayer;
import net.codejava.model.remo;
import net.codejava.model.FollowerLists.FollowerList;
import net.codejava.model.FollowerLists.FollowingList;
import net.codejava.model.FollowerLists.SearchUser;
import net.codejava.model.FollowerLists.Story;
import net.codejava.model.FollowerLists.statusess;
import net.codejava.model.Views.StatusViews;
import net.codejava.model.Views.s;
import net.codejava.service.StreamingService;
import net.codejava.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController extends Thread {
	@Autowired
	private AccountLockRepository FRp;
	@Autowired
	private UserCallService carservice;
	@Autowired
	private FollowerRequestService FRS;
	@Autowired
	private CustomVariableRepository CVR;
	@Autowired
	private MyCallable callable;
	@Autowired 
	private JUserCallable Jcallable;
	@Autowired
	ThreadPoolTaskExecutor threadPool;
	@Autowired
	private StringCallable Strac;
	@Autowired
	private StoryRepository Ssrepo;
	@Autowired
	private JRoleRepository Rrepo;
	@Autowired
	private StatusViewerRepository Strepo;
	@Autowired
	private JUserRepository Urepo;
	@Autowired
	private OneTORepository ur;
	@Autowired
	private UserRepository uu;
	@Autowired
	private ImageRepository ir;
   @Autowired
   private CoverProfileRepository cr;
   @Autowired
	PasswordEncoder encoder;
   @Autowired
   ProfilePhotoRepository pr;
  @Autowired
  CoverPhotoRepository ccr;
  @Autowired
  private JavaMailSender mailsender;
  @Autowired
  private StatusesRepository Srepo;
  @Autowired
  private UserService ser;
  @Autowired
  private ListOfIndexesProcedure LSP;
 

  private static final org.jboss.logging.Logger LOGGER = LoggerFactory.logger(TestController.class);
	@GetMapping("/all")
	public ResponseEntity<List<JUser>> allAccess() {
		List<JUser> listuser=Urepo.findAll();
		
		return new ResponseEntity<List<JUser>>(listuser,HttpStatus.OK);
	}
//	@GetMapping("/allActiveUsers")
//	public ResponseEntity<List<JUser>> AllActive() {
//		List<JUser> listuser=Urepo.findAllActiveUsers();
//		
//		return new ResponseEntity<List<JUser>>(listuser,HttpStatus.OK);
//	}
	@GetMapping("/fi/{id}")
	public ResponseEntity<JUser> allProfiles(@PathVariable("id") Long id) throws ParseException{
		
		JUser user =Urepo.findById(id).get();
		/*Date da=user.getAccountCreationDate();
		String d=new SimpleDateFormat("dd/mm/yyyy").format(da);
		Date nDate=new SimpleDateFormat("dd/mm/yyyy").parse(d);
		user.setAccountCreationDate(nDate);*/
		//user.setAccountCreationDate(new SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH).format(da));
		
		return new ResponseEntity<JUser>(user,HttpStatus.OK);
	}
	@GetMapping("/book/{id}")
	public ImageProfile getStudent(@PathVariable("id") Long id)
	{
		if(!Urepo.existsById(id)) {
			throw new UserNotFoundException("student Not Found exception");
		}
		List<ImageProfile> auth=ir.findByUserId(id);
		if(auth.size() >0)
		{
			return auth.get(0);
		}
		else
		{
			throw new  UserNotFoundException("not found");
		}
	}
	@PutMapping("/update/Only/data/{id}")
	public ResponseEntity<JUser> updateSingleUSerData(@PathVariable("id") Long id,
			@RequestBody JUser user)
	{
		JUser use=Urepo.findById(id).get();
		
		use.setUsername(user.getUsername());
		use.setEmail(user.getEmail());
		use.setGender(user.getGender());
		use.setAccountCreationDate(user.getAccountCreationDate());
		//PerminantAddress address=new PerminantAddress();
		use.setPadress(user.getPadress());
		Urepo.save(use);
		return new ResponseEntity<JUser>(use,HttpStatus.OK);
		}
	@GetMapping("/cover/{id}")
	public CoverProfile getCoverWithStudent(@PathVariable("id") Long id)
	{
		if(!Urepo.existsById(id)) {
			throw new UserNotFoundException("student Not Found exception");
		}
		List<CoverProfile> auth=cr.findByUserId(id);
		if(auth.size() >0)
		{
			return auth.get(0);
		}
		else
		{
			throw new  UserNotFoundException("not found");
		}
	}
	@GetMapping("/cov/{id}")
	public CoverProfile getOne(@PathVariable("id") Long  id)
	{
		@SuppressWarnings("deprecation")
		CoverProfile cov=cr.getOne(id);
		
		return cov;
	}
	/*
	 * 
	 * 
	 * ####################################################################
	 * ###################################################################
	 * To GET USER BY USERNAME FOR SEARCHING PURPOSE
	 * 
	 * #####################################################################
	 * #####################################################################
	 */
	
	@GetMapping("get/profile/{username}")
	public ResponseEntity<JUser> getProfilePicture(@PathVariable("username") String username)
	{
		JUser user=Urepo.findByUsername(username).get();
		
		return new ResponseEntity<JUser>(user,HttpStatus.OK);
	}
	@PutMapping("/upload/profilepicture/{id}")
	public ResponseEntity<String> UploadProfilePicture(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multiPartFile) throws IOException
	{
		JUser user=Urepo.findById(id).get();
		user.setProfilePic(multiPartFile.getBytes());
		Urepo.save(user);
		return new ResponseEntity<String>("Profie Picture is Uploaded SuccessFully",HttpStatus.OK);
	}
	@PutMapping("/upload/coverpicture/{id}")
	public ResponseEntity<String> UploadCoverPicture(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multiPartFile) throws IOException
	{
		JUser user=Urepo.findById(id).get();
		user.setCoverPic(multiPartFile.getBytes());
		Urepo.save(user);
		return new ResponseEntity<String>("Cover Picture is Uploaded SuccessFully for"+user.getUsername(),HttpStatus.OK);
	}
	@PutMapping("/update/cover/{id}")
	public CoverProfile getOneUpd(@PathVariable("id") Long  id,@RequestBody CoverProfile prof)
	{
		@SuppressWarnings("deprecation")
		CoverProfile cov=cr.getOne(id);
		
		cov.setUrl(prof.getUrl());
		
		cr.save(cov);
		
		return cov;
	}
	/*
	 * getting cover photo Url wise
	 */
	@GetMapping("coverpicture/user/{id}")
	public ResponseEntity<byte[]> fromDatabas(@PathVariable("id") Long id) 
	        throws SQLException {

		Optional<JUser> primeMinisterOfIndia = Urepo.findById(id);
		byte[] imageBytes = null;
		if (primeMinisterOfIndia.isPresent()) {
		
			imageBytes = primeMinisterOfIndia.get().getCoverPic();
		}
		
	return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
		
	}
	/*
	 * #################################################################
	 * #################################################################
	 * 
	 * FOR DELETING AN EXISTING USER
	 * 
	 * #################################################################
	 * #################################################################
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id" )Long id)
	{
		Urepo.deleteById(id);	
		return new ResponseEntity<String>("user with following id is deleted"+id,HttpStatus.OK);
	}
   /*
    * #####################################################################
    * #####################################################################
    * 
    * FOR DELETING A ROLE OF AN EXISITNG USER
    * 
    * ######################################################################
    * ######################################################################
    * 
    */
	@DeleteMapping("/delete/role/{id}/{idd}")
	public ResponseEntity<String> deleteExistingUser(@PathVariable("id" )int id,@PathVariable("id")Long idd)
	{
		
		JUser user=Urepo.getById(idd);
		 JRole role=new JRole(id);
		 user.remoRole(role);
			
		return new ResponseEntity<String>("user with following id is "+user+"with role id is deleted with "+id,HttpStatus.OK);
	}
	/*#############################################################################
	 * ############################################################################
	 * FOR UPDATING AN EXISITING USER DATA 
	 * 
	 * ############################################################################
	 * ############################################################################
	 */
	@PutMapping("/update/user/{id}")
	public ResponseEntity<?> registerUser(@PathVariable("id") Long id,@RequestBody UpdateRequest request)
	{
	  JUser juser=Urepo.getById(id);
	  
	  juser.setUsername(request.getUsername());
	  juser.setPassword(encoder.encode(request.getPassword()));
	  Set<String> strRoles = request.getRole();
		Set<JRole> roles = new HashSet<>();

		if (strRoles == null) {
			JRole userRole = Rrepo.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					JRole adminRole = Rrepo.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					JRole modRole = Rrepo.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					JRole userRole = Rrepo.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		juser.setRoles(roles);

	  Urepo.save(juser);
		
		return ResponseEntity.ok(new MessageResponse("User Updated Successfully successfully!"));
	}
	@PutMapping("/update/user/all/{id}")
	public ResponseEntity<?> UpdateUser(@PathVariable("id") Long id,@RequestBody UpdateRequest request)
	{
	  JUser juser=Urepo.getById(id);
	  if (Urepo.existsByUsername(request.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (Urepo.existsByEmail(request.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
	  juser.setUsername(request.getUsername());
	  juser.setPassword(encoder.encode(request.getPassword()));
	 juser.setEmail(request.getEmail());

	  Urepo.save(juser);
		
		return ResponseEntity.ok(new MessageResponse("User Updated Successfully successfully!"));
	}
//	@PostMapping("/sav")
//	public OneTO saveUsers(@RequestBody OneTO user)
//	{
//		
//		return ur.save(user);
//		
//	}
	@PostMapping("/sav")
	public User saveUsers(@RequestBody User user)
	{
		
		return uu.save(user);
		
	}
	
	@PostMapping("/Book/{id}/img")
	public ImageProfile addAuthor(@PathVariable("id") Long id,@RequestBody ImageProfile image)
	{
		
		return Urepo.findById(id).map(book ->{
			image.setUser(book);
			return ir.save(image);
		}).orElseThrow(() -> new UserNotFoundException("not found"));
		
	}
	@PostMapping("/cover/{id}/pro")
	public CoverProfile addCover(@PathVariable("id") Long id,@RequestBody CoverProfile profile)
	{
		
		return Urepo.findById(id).map(book ->{
			profile.setUser(book);
			return cr.save(profile);
		}).orElseThrow(() -> new UserNotFoundException("not found"));
		
	}
	public static 	final String DIRECTORY1= System.getProperty("user.home")+"/Documents/CoverProfiles/";
	@PostMapping("/upload/{id}/cover")
	public CoverProfile  uploadCoverProfile(@PathVariable("id") Long id,@RequestParam("file")  MultipartFile multipartFiles) throws IOException
	{
		CoverProfile coverprofile=new CoverProfile();
		
		
		MultipartFile file=multipartFiles;
		 
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
	
		Path fileStorage = Paths.get(DIRECTORY1,filename).toAbsolutePath().normalize();
		Files.copy(file.getInputStream(), fileStorage,StandardCopyOption.REPLACE_EXISTING);
	  String url="http://localhost:8090/api/test/show/";
		
		
		
		 url=url+filename;
		 
		 coverprofile.setUrl(url);
		 
		
		 return Urepo.findById(id).map(book ->{
			 coverprofile.setUser(book);
				return cr.save(coverprofile);
			}).orElseThrow(() -> new UserNotFoundException("not found"));
			
		 
	//return ResponseEntity.ok().body(filenames);
		
	}
	@GetMapping("/show/{filename}")
	public ResponseEntity<Resource> showProfile(@PathVariable("filename") String filename) throws IOException
	{
		Path filePath=Paths.get(DIRECTORY1).toAbsolutePath().normalize().resolve(filename);
		
		if(!Files.exists(filePath))
				{
			            throw new FileNotFoundException(filename +"filenot found Exception");
				}
		  Resource resource = new UrlResource(filePath.toUri());
		  HttpHeaders httpHeaders= new HttpHeaders();
		  
		  httpHeaders.add("filename", filename);
		 
		  String headerKey="content-Disposition";
			String  headerValue="attachment;filename="+resource.getFilename();
		  httpHeaders.add(headerKey,headerValue);
		  return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
				  .headers(httpHeaders).body(resource);
		
	}
	@PutMapping("/update/{id}/img")
	public ImageProfile  updateImage(@PathVariable("id") Long id,@RequestParam("file")  MultipartFile multipartFiles) throws IOException
	{
		ImageProfile imagestore=new ImageProfile();
		
		
		MultipartFile file=multipartFiles;
		 
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
	
		Path fileStorage = Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();
		Files.copy(file.getInputStream(), fileStorage,StandardCopyOption.REPLACE_EXISTING);
	  String url="http://localhost:8090/api/test/down/";
		
		
		
		 url=url+filename;
		 
		 imagestore.setUrl(url);
		 
		
		 return Urepo.findById(id).map(book ->{
				imagestore.setUser(book);
				return ir.save(imagestore);
			}).orElseThrow(() -> new UserNotFoundException("not found"));
			
	}
	
	
	@PostMapping("/upload/{id}/img")
	public ImageProfile  uploadImage(@PathVariable("id") Long id,@RequestParam("file")  MultipartFile multipartFiles) throws IOException
	{
		ImageProfile imagestore=new ImageProfile();
		
		
		MultipartFile file=multipartFiles;
		 
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
	
		Path fileStorage = Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();
		Files.copy(file.getInputStream(), fileStorage,StandardCopyOption.REPLACE_EXISTING);
	  String url="http://localhost:8090/api/test/down/";
		
		
		
		 url=url+filename;
		 
		 imagestore.setUrl(url);
		 
		
		 return Urepo.findById(id).map(book ->{
				imagestore.setUser(book);
				return ir.save(imagestore);
			}).orElseThrow(() -> new UserNotFoundException("not found"));
			
		 
	//return ResponseEntity.ok().body(filenames);
		
	}
	@PostMapping("/upload/file")
	public ResponseEntity<String> uploadProfilePhotoToUser(@RequestParam("file" ) MultipartFile multipartFiles) throws IOException
	{
		
		String filename=StringUtils.cleanPath(multipartFiles.getOriginalFilename());
		
		ProfilePhoto profile=new ProfilePhoto();
		
		profile.setName(filename);
		profile.setContent(multipartFiles.getBytes());
		profile.setUploadDate(new Date());
		profile.setSize(multipartFiles.getSize());
		pr.save(profile);
		
		return new ResponseEntity<String>("File is Uploaded successfully and size is"+multipartFiles.getSize()+" :)",HttpStatus.OK);
	}
	@PutMapping("/update/file/{id}")
	public ResponseEntity<String> updateProfilePhotoToUser(@RequestParam("file" ) MultipartFile multipartFiles,@PathVariable("id") Long id) throws IOException
	{
		ProfilePhoto photo=pr.getById(id);
		String filename=StringUtils.cleanPath(multipartFiles.getOriginalFilename());
		
		
		photo.setName(filename);
		photo.setContent(multipartFiles.getBytes());
		photo.setUploadDate(new Date());
		photo.setSize(multipartFiles.getSize());
		pr.save(photo);
		
		return new ResponseEntity<String>("File is Updated successfully and size is"+multipartFiles.getSize()+" :)",HttpStatus.OK);
	}
	@GetMapping("get/file/{id}")
	public ProfilePhoto seeEachProfilePhoto(@PathVariable("id") Long id)
	{
		
	          final Optional<ProfilePhoto> retrievedImage = pr.findById(id);
	          ProfilePhoto img = new ProfilePhoto(retrievedImage.get().getId(),retrievedImage.get().getName(),
	                  retrievedImage.get().getContent(),retrievedImage.get().getUploadDate(),retrievedImage.get().getSize());
	          return img;
	      }
	
	public static 	final String DIRECTORY= System.getProperty("user.home")+"/Downloads/uploads/";
	@PostMapping("/uploadfile")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("file")  MultipartFile multipartFiles) throws IOException
	{
		List<String> filenames= new ArrayList<>();
		
	
			MultipartFile file=multipartFiles;
			 
			String filename=StringUtils.cleanPath(file.getOriginalFilename());
		
			Path fileStorage = Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();
			Files.copy(file.getInputStream(), fileStorage,StandardCopyOption.REPLACE_EXISTING);
			
			filenames.add(filename);
			
			 String url="http://localhost:9999/down/";
		return ResponseEntity.ok().body(filenames);
			
	}

	@GetMapping("/down/{filename}")
	public ResponseEntity<Resource> DownloadFile(@PathVariable("filename") String filename) throws IOException
	{
		Path filePath=Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
		
		if(!Files.exists(filePath))
				{
			            throw new FileNotFoundException(filename +"filenot found Exception");
				}
		  Resource resource = new UrlResource(filePath.toUri());
		  HttpHeaders httpHeaders= new HttpHeaders();
		  
		  httpHeaders.add("filename", filename);
		 
		  String headerKey="content-Disposition";
			String  headerValue="attachment;filename="+resource.getFilename();
		  httpHeaders.add(headerKey,headerValue);
		  return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
				  .headers(httpHeaders).body(resource);
		
	}
	
	@GetMapping("/getl")
	public List<OneTO> getli()
	{
		return ur.findAll();
	}
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/area")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccessadd() {
		return "register";
	}
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<JUser>> findAll()
	{
		List<JUser> l=Urepo.findAll();
		return new ResponseEntity<List<JUser>>(l,HttpStatus.OK);
	}
	
	@PostMapping("/upload/cover/file")
	public ResponseEntity<String> UploadCoverPhotoToDB(@RequestParam("file") MultipartFile multipartFiles) throws IOException
	
	{
		CoverPhoto cover=new CoverPhoto();
		String filename=StringUtils.cleanPath(multipartFiles.getOriginalFilename());
		cover.setName(filename);
		cover.setContent(multipartFiles.getBytes());
		cover.setSize(multipartFiles.getSize());
		cover.setUploadedDate(new Date());
		
		ccr.save(cover);
		
		return new ResponseEntity<String>("coverPhoto is Uploaded SuccessFully....................... :)",HttpStatus.OK);
	}
	@SuppressWarnings("unused")
	@GetMapping("get/cover/photo/{id}")
	public ResponseEntity<CoverPhoto> showCoverPhoto(@PathVariable("id") Long id)
	{
		final Optional<CoverPhoto> cover=ccr.findById(id);
		
		CoverPhoto coverphoto=new CoverPhoto(cover.get().getId(),cover.get().getName(),
				cover.get().getUploadedDate(),cover.get().getSize(),
				cover.get().getContent());
		if(coverphoto == null)
		{
			return new ResponseEntity<CoverPhoto>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CoverPhoto>(coverphoto,HttpStatus.OK);
	}
	/*
	 * ##########################################################################
	 * ##########################################################################
	 * 
	 * FOR UPDATING THE COVER PICTURE OF AN EXISITNG USER
	 * 
	 * ##########################################################################
	 * ##########################################################################
	 */
	@PutMapping("/update/Cover/photo/{id}")
	public ResponseEntity<String> updateCoverPhoto(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multipartFiles)
	{
		CoverPhoto photo=ccr.getById(id);
		
		try {
			photo.setContent(multipartFiles.getBytes());
			photo.setUploadedDate(new Date());
			} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		photo.setName(StringUtils.cleanPath(multipartFiles.getOriginalFilename()));
		photo.setSize(multipartFiles.getSize());
		ccr.save(photo);
		return new ResponseEntity<String>("CoverPhoto has been updated successFully",HttpStatus.OK);
	}
	@SuppressWarnings("deprecation")
	@GetMapping("cover/database/{id}")
	public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Long id) 
	        throws SQLException {

		Optional<CoverPhoto> primeMinisterOfIndia = ccr.findById(id);
		byte[] imageBytes = null;
		if (primeMinisterOfIndia.isPresent()) {
		
			imageBytes = primeMinisterOfIndia.get().getContent();
		}
		
	return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
		
	}
	
/* This end point is to upload the profile photo of the  particular User
 * we have to use user id as the path variable
 * */
	@PostMapping("/upload/user/profile/{id}/photo")
	public ProfilePhoto  UploadUsersProfilePhoto(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multipartFile) throws IOException
	{
		
		ProfilePhoto cover=new ProfilePhoto();
		String filename=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		cover.setName(filename);
		cover.setContent(multipartFile.getBytes());
		cover.setSize(multipartFile.getSize());
		cover.setUploadDate(new Date());
		
		/*
		 * 	The problem here is you are persisting the parent table with the values set .
		 *  When it goes to persist it needs the child table id which has to be persisted 
		 * already as it was a foreign key and hence it was a not 
		 * null property references a null value
		 */
		/*
		 * we shouldn't save the entity whenever we are mapping any other entity 
		 */
		return Urepo.findById(id).map(book ->{
		cover.setUser(book);
			return pr.save(cover);
		}).orElseThrow(() -> new UserNotFoundException("not found"));
		
		
	}
	@Autowired
	private ProfilePictureRepository pof;
	@PostMapping("/upload/user/picture/{id}/photo")
	public ProfilePicture  UploadUsersProfilePicture(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multipartFile) throws IOException
	{
		
		ProfilePicture cover=new ProfilePicture();
		String filename=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		cover.setName(filename);
		cover.setContent(multipartFile.getBytes());
		cover.setSize(multipartFile.getSize());
		cover.setUploadDate(new Date());
		
		/*
		 * 	The problem here is you are persisting the parent table with the values set .
		 *  When it goes to persist it needs the child table id which has to be persisted 
		 * already as it was a foreign key and hence it was a not 
		 * null property references a null value
		 */
		/*
		 * we shouldn't save the entity whenever we are mapping any other entity 
		 */
		return Urepo.findById(id).map(book ->{
		cover.setUser(book);
			return pof.save(cover);
		}).orElseThrow(() -> new UserNotFoundException("not found"));
		
		
	}
	@GetMapping("/get/picture/{id}")
	public ResponseEntity<ProfilePicture> getProfilePicture(@PathVariable("id") Long id)
	{
		ProfilePicture pic=pof.findById(id).get();
				
				return new ResponseEntity<ProfilePicture>(pic,HttpStatus.OK);
	}
	/*
	 * here we are configuring cover photo upload for existing user by getting id of the user from the path
	 * 	
	 */
	@PostMapping("upload/cover/photo/{id}/existinguser")
	public CoverPhoto uploadCoverPhotoforExistingUser(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multipartFile) throws IOException
	{
		CoverPhoto cover=new CoverPhoto();
		cover.setContent(multipartFile.getBytes());
		cover.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		cover.setSize(multipartFile.getSize());
		cover.setUploadedDate(new Date());
		
		return Urepo.findById(id).map(user->
		{
			cover.setUser(user);
			return ccr.save(cover);

		}).orElseThrow(()->new
				UserNotFoundException("User with the following id number"+
		id+" is not found try first with "
		+ "register user the upload CoverPhoto.......... :)"));
		
		
	}
	/*
	 * 
	 * 
	 */
	
	 
	
	 
	/*
	 * Posts Declaring
	 */
	 @Autowired
	 private PostRepository pos;
	 
	 @PostMapping("/upload/{id}/{description}/post")
	 public ResponseEntity<Posts> addPostToExisitingUser(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multipartfile,@PathVariable String description) throws IOException
	 {
		 Posts posts=new Posts();
		
		 posts.setUploadedTime(new Date());
		 posts.setContent(multipartfile.getBytes());
		 posts.setDescription(description);
		 JUser user=Urepo.findById(id).get();
		 posts.setUser(user);
		 
		 pos.save(posts);
		 
		 return new ResponseEntity<Posts>(HttpStatus.OK);
	 }
	 
	 @GetMapping("/get/AllPosts")
	 public ResponseEntity<List<Posts>> getAllPostsOfAllTheUsers()
	 {
		 List<Posts> list=pos.findAll();
		 
		 return new ResponseEntity<List<Posts>>(list,HttpStatus.OK);
	 }
	 @GetMapping("/get/AllPosts/{id}")
	 public ResponseEntity<Posts> getAllPostsOfAllTheUser(@PathVariable("id") Long id)
	 {
		 Posts list=pos.findById(id).get();
		 
		 return new ResponseEntity<Posts>(list,HttpStatus.OK);
	 }
	 @DeleteMapping("/delete/post/{id}")
	 public ResponseEntity<String> deletePostById(@PathVariable("id") Long id)
	 {
		 Posts po=pos.findById(id).get();
		 
		 pos.delete(po);
		 
		 return new ResponseEntity<String>("psot is Deleted Successfully....:)",HttpStatus.OK);
	 }
	 @Autowired 
	 private StatusRepository st;
	 @PostMapping("/user/status/{id}")
	 public ResponseEntity<Status> updateStatus(@PathVariable("id") Long id,@RequestBody StatusRequest status)
	 {
		JUser user = Urepo.findById(id).get();
		
		Status stat=new Status();
		
		Map<Integer,String> statu= new HashMap<>();
		stat.setUser(user);
		statu.put(1, "offline");
		if(status.getStatus() == statu.get(1))
		{
			stat.setStatus(1);
			st.save(stat);
			
		}
		else {
			stat.setStatus(2);
			st.save(stat);
		}
		 return new ResponseEntity<Status>(HttpStatus.OK);
	 }
	 @Autowired
	 private PostmanRepository pooo;
	
	 @PostMapping("post/postman")
	 public ResponseEntity<Postman> addPostman(@RequestBody Postman postman)
	 {
		 Postman po=new Postman();
		 
		 po.setName(postman.getName());
		 
		 pooo.save(po);
		 
		 return new ResponseEntity<Postman>(po,HttpStatus.OK);
		 
	 }
	 @Autowired
	 private RemoRepository remo;
	 @PostMapping("/Book/{id}/post")
	 public remo addAuthor(@PathVariable("id") Long id,@RequestBody remo author) throws NotFoundException
	 {
	 	
	 	return pooo.findById(id).map(book ->{
	 		author.setPostman(book);
	 		return remo.save(author);
	 	}).orElseThrow(() -> new NotFoundException("not found"));
	 	
	 }
	 @GetMapping("get/postman/{id}")
	 public ResponseEntity<Postman> getPostman(@PathVariable("id") Long id)
	 {
		 Postman post=pooo.findById(id).get();
		 
		 return new ResponseEntity<Postman>(post,HttpStatus.OK);
	 }
	 @GetMapping("get/remo/{id}")
	 public ResponseEntity<remo> getPostmanremo(@PathVariable("id") Long id)
	 {
		 remo post=remo.findById(id).get();
		 
		 return new ResponseEntity<remo>(post,HttpStatus.OK);
	 }
	 @Autowired
	 private AdressRepository ads;
	 @PostMapping("/get/adree/{id}")
	 public Adress postAdress(@PathVariable("id") Long id,@RequestBody Adress adress) throws NotFoundException
	 {
		 return Urepo.findById(id).map(book ->{
		 		adress.setUser(book);
		 		return ads.save(adress);
		 	}).orElseThrow(() -> new NotFoundException("not found"));
		 
	 }
	 @Autowired 
	 private PerminantAddressRepository pad;
	 
	// public static PerminantAddress address = new PerminantAddress();
	 @PostMapping("/post/perminant/{id}")
	 public PerminantAddress addPerminantAddress(@PathVariable("id") Long id,
			 @RequestBody PerminantAddress adress  ) throws NotFoundException
	 {
		 PerminantAddress address = new PerminantAddress();
		 address.setVillage(adress.getVillage());
		 address.setCountry(adress.getCountry());
		 address.setDistrict(adress.getDistrict());
		 address.setLandmark(adress.getLandmark());
		 address.setState(adress.getState());
		 address.setTaluka(adress.getTaluka());
		 address.setStreet(adress.getStreet());
		 
		 
		 return Urepo.findById(id).map(user->{
			 address.setUser(user);
			 return pad.save(address);
			 }).orElseThrow(()-> new NotFoundException("user Not Found"));
				 		 
	 }
	 @GetMapping("get/AddressPermianat/{id}")
	 public ResponseEntity<PerminantAddress> getOnePerminanatAddress(@PathVariable("id") Long id)
	 {
		 PerminantAddress address = new PerminantAddress();
		address = pad.findById(id).get();
		return new ResponseEntity<PerminantAddress>(address,HttpStatus.OK);
	 }
	 @Autowired
		JobLauncher jobluancher;
		@Autowired
		Job job;
		@Autowired
		private StreamingService Sserv;
		
		
		@GetMapping("/load")
		public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
		{
			Map<String,JobParameter> maps= new HashMap<>();
		maps.put("time",new JobParameter(System.currentTimeMillis()));

		JobParameters parameters = new JobParameters(maps);

		JobExecution jobExecution=jobluancher.run(job, parameters);

		System.out.println("JobExecution"+jobExecution.getStatus());
		System.out.println("Batch Is  Running");
		while(jobExecution.isRunning())
		{
			System.out.println("...");
		}
		return jobExecution.getStatus();


		}
		/*
		 * Product Implementation and Each Product Has multiple Images 
		 */
		@Autowired
		private ProductItemRepository productrepo;
		@PostMapping("/add/product/{quantity}/{productName}")
		public ResponseEntity<Product> addProduct(@RequestParam("file") MultipartFile multipartFile,@PathVariable("quantity") String quantity,@PathVariable("productName") String productName) throws IOException
		{
			Product product=new Product();
			product.setProductContent(multipartFile.getBytes());
			product.setProductName(productName);
			product.setQuantity(quantity);
			
			productrepo.save(product);
			
			return new ResponseEntity<Product>(product,HttpStatus.OK);
			
		}
		@Autowired
		private ProductExtendRepository pre;
		@PostMapping("add/extend/upload/{id}")
		public ResponseEntity<List<String>> addExtendToAlreadyProduct(@PathVariable("id") Long id,@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException
		{
			List<String> names=new ArrayList<>();
			Product product=productrepo.findById(id).get();
			
			
			
			for(MultipartFile file :multipartFiles)
			{
				ProductExtend productExtend=new ProductExtend();
				productExtend.setContent(file.getBytes());
				productExtend.setProduct(product);
				productExtend.setParentname(product.getProductName());
				names.add(StringUtils.cleanPath(file.getOriginalFilename()));
				
				pre.save(productExtend);
			}
			return new ResponseEntity<List<String>>(names,HttpStatus.OK);
		}
		
	/*
	 * Forgot Password Reset
	 */
		@Autowired
		private UserService userservice;
		@PostMapping("/forgotPassword/{email}")
		public ResponseEntity<String> processForgotPassword(@PathVariable("email") String email,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException
		{
			String token=RandomString.make(45);
			try {
			userservice.resetUpdatePassword(token, email);
			}
			catch(Exception e)
			{
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
			}
			System.out.println("Email:"+email);
			System.out.println("Token:"+token);
			String resetPasswordLink=Utility.getSiteUrl(request);
			
			String resetPassword="http://localhost:8081/password/reset/"+"token?="+token;///password/reset/token?=:token
			String subject="Here is the link to Reset Password";
			String content="<p>Hello,</p>"
					+ "<p>You  Have Requested To Reset Your Account Password</p>"
					+ "<p>Click On the Below Link To Reset Your Password</p>"
					+"<button style={{background-color:'blue'}}><a href=\""+resetPassword+"\">change My Password</a></button>";
			
			
			sendEmail(email,resetPassword,subject,content);
			
			System.out.println(resetPasswordLink+"/reset_password?token="+token);
			return new ResponseEntity<String>("Email Has Been Sent To Your Email",HttpStatus.OK);
			}
		private void sendEmail(String email, String resetPassword,String subject,String content) throws UnsupportedEncodingException, MessagingException {
			// TODO Auto-generated method stub
			MimeMessage message=mailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			
			helper.setFrom("abhiram7997686@gmail.com", "Ramsai");
			helper.setTo(email);
			//String subject="Here is the link to Reset Password";
			
			/*
			 * String content="<p>Hello,</p>"
					+ "<p>You  Have Requested To Reset Your Account Password</p>"
					+ "<p>Click On the Below Link To Reset Your Password</p>"
					+"<button style={{background-color:'blue'}}><a href=\""+resetPassword+"\">change My Password</a></button>";*/
					
			
			helper.setSubject(subject);
			helper.setText(content,true);
			
			mailsender.send(message);
				
			
		}
		
		@GetMapping("verify/{token}")
		public ResponseEntity<String> VerifyTokenSentBytheClientMachine(@PathVariable("token") String token)
		{
			JUser user=userservice.get(token);
			
			if(user != null)
			{
				return new ResponseEntity<String>("Token is Valid You can Reset Your Password",HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Token is Invalid Please Request Again after some Time",HttpStatus.NOT_FOUND);
				
			}
						
		}
		@PostMapping("reset/password/{newPassword}/{token}")
		public ResponseEntity<String> ResetPassword(@PathVariable("newPassword") String newPassword,@PathVariable("token") String token)
		
		{
			JUser user=userservice.get(token);
			
			if(user != null)
			{
			userservice.updatePassword(user, newPassword);
			}
			else {
				return new ResponseEntity<String>("invalid Token",HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<String>("you password was Changed SuccessFully :)",HttpStatus.OK);
		}
		@PostMapping("/verify/Email/{email}")
		public ResponseEntity<String> VerifyEmailOfThePerson(@PathVariable("email") String email) throws UnsupportedEncodingException, MessagingException
		{
			String token=RandomString.make(45);
			int max=400;
			int min=200;
			int b = (int)(Math.random()*(max-min+1)+min);
			userservice.sendVerifyLink(email, token,b);
			//String VerificationLink="http://localhost:8081/prac";
			String subject="Verification Link";
			String VerificationLink="http://localhost:8081/EmailVerification/"+"token?="+token;
			String content="<p>Hello,</p>"
					
					+ "<p>Click On the Below Link To Verify You Emai</p>"
					+"<p style=background-color:red>Use Below Otp To verify Your Email</p>"+b
					+"<button style=background-color:#6495ED;width:150px;height:30px;outline:none;padding:none;border:none;border-radius:20%><a style=text-decoration:none;outline:none href=\""+VerificationLink+"\">change My Password</a></button>";
			
			sendEmail(email,VerificationLink,subject,content);
			
			
			
			return new  ResponseEntity<String>("Verification Link Has been Sent To your Email Please check",HttpStatus.OK);
			
		}
		@Autowired
		private EmailVerificationRepository emailrepo;
		@SuppressWarnings("unused")
		@PostMapping("/verify/Token/otp/{token}/{otp}")
		public ResponseEntity<String> VerifyOtp(@PathVariable("token") String token,@PathVariable("otp") int otp)
		{
			EmailVerification verify=emailrepo.findByToken(token);
			Date expiryDate=new Date();
			long differenceintime=expiryDate.getTime()-(verify.getStartTime().getTime());
			long difference=Math.abs((differenceintime
	                   / (1000 * 60))
	                  % 60);
			if(verify != null)
			{	
				if(difference <=3)
				{
						if(verify.getOtp()==otp)
						{
							
							return new ResponseEntity<String>("session has created You can fill Furthur Details"+difference+" in minutes "
									+verify.getStartTime(),HttpStatus.OK);
						}
						else {
							return new ResponseEntity<String>("session Has Expired Please Request Otp Again",HttpStatus.OK);
						}
				}
				else {
					emailrepo.deleteById(verify.getId());
					return new ResponseEntity<String>("Otp Has Expired Please Request Again Time Out"+differenceintime,HttpStatus.OK);
				}
			}
			else {
			  return new ResponseEntity<String>("session has expired",HttpStatus.OK);
			}
		}
		public static boolean isNumeric(String string) {
		    int intValue;
				
		    System.out.println(String.format("Parsing string: \"%s\"", string));
				
		    if(string == null || string.equals("")) {
		        System.out.println("String cannot be parsed, it is null or empty.");
		        return false;
		    }
		    
		    try {
		        intValue = Integer.parseInt(string);
		        return true;
		    } catch (NumberFormatException e) {
		        System.out.println("Input String cannot be parsed to Integer.");
		    }
		    return false;
		}
		
		@PostMapping("/upload/bulk/photo")
		public ResponseEntity<List<String>> uploadMultipleFilesForMultipleUser(@RequestParam("file") List<MultipartFile> multipartfile) throws IOException
		{
			List<String> filenames=new ArrayList<>();
			//@SuppressWarnings("unchecked")
			List<JUser> users=new ArrayList<>();
			
			for(MultipartFile file : multipartfile)
			{
				String filename=StringUtils.cleanPath(file.getOriginalFilename());
				filenames.add(filename);
				String name=filename.substring(0, filename.indexOf('.'));
				
				System.out.println(isNumeric(name));
				if(!isNumeric(name)) {
					JUser user=Urepo.findByUsername(name).get();
					user.setCoverPic(file.getBytes());
					Urepo.save(user);
					users.add(user);
					System.out.println("if Block");
					
				}
				else {
				
				long id=Long.parseLong(name);
				System.out.println("else Block ");
				
				try {
					
				JUser user=Urepo.findById(id).get();
				user.setCoverPic(file.getBytes());
				Urepo.save(user);
				users.add(user);
				}
				catch(Exception e)
				{
					List<String> ex=new ArrayList<>();
					List<Long> ids=new ArrayList<>();
					ids.add(id);
					ex.add(e.getMessage()+"........"+"id is not found in the System"+ids);
					return new ResponseEntity<List<String>>(ex,HttpStatus.NOT_FOUND);
							
				}
			}
				
				
			}
			return new ResponseEntity<List<String>>(filenames,HttpStatus.OK);
			
		}
		@Autowired
		private VideoPlayerRepository play;
		
		
		public static 	final String VIDEO_DIRECTORY= System.getProperty("user.home")+"/Downloads/uploads/";
		@PostMapping("/post/video")
		public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile multipartfile) throws IOException
		{
			
			VideoPlayer player=new VideoPlayer();
			String url="http://localhost:8090/api/test/player/play";
			String filename=StringUtils.cleanPath(multipartfile.getOriginalFilename());
			player.setFilename(filename);
			player.setUrl(url);
			Path filestorage=Paths.get(VIDEO_DIRECTORY,filename).toAbsolutePath().normalize();
			Files.copy(multipartfile.getInputStream(), filestorage, StandardCopyOption.REPLACE_EXISTING);
			play.save(player);
			
			return new ResponseEntity<String>("video is Uploaded Successfully",HttpStatus.OK);
						
		}
	
		
		
		@GetMapping("player/play/{filename}")
		public ResponseEntity<Resource> PlayVideo(@PathVariable("filename") String filename) throws IOException
	      {
			Path filepath=Paths.get(VIDEO_DIRECTORY).toAbsolutePath().normalize().resolve(filename);
			
			if(!Files.exists(filepath))
			{
		            throw new FileNotFoundException(filename +"filenot found Exception");
			}
	  Resource resource = new UrlResource(filepath.toUri());
	  HttpHeaders httpHeaders= new HttpHeaders();
	  
	  httpHeaders.add("filename", filename);
	 
	  String headerKey="content-Disposition";
		String  headerValue="attachment;filename="+resource.getFilename();
	  httpHeaders.add(headerKey,headerValue);
	  return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filepath)))
			  .headers(httpHeaders).body(resource);
		}
		@GetMapping("player/stream/{filename}")
		public ResponseEntity<Resource> PlayVideod(@PathVariable("filename") String filename) throws IOException
	      {
			Path filepath=Paths.get(VIDEO_DIRECTORY).toAbsolutePath().normalize().resolve(filename);
			
			if(!Files.exists(filepath))
			{
		            throw new FileNotFoundException(filename +"filenot found Exception");
			}
	  Resource resource = new UrlResource(filepath.toUri());
	  HttpHeaders httpHeaders= new HttpHeaders();
	  
	  httpHeaders.add("filename", filename);
	  httpHeaders.set("Accept-Ranges", "bytes");
	  httpHeaders.set("Content-Range", "bytes 50-1025/17839845");
	  
	 
	  String headerKey="content-Disposition";
		String  headerValue="attachment;filename="+resource.getFilename();
	  httpHeaders.add(headerKey,headerValue);
	  return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filepath)))
			  .headers(httpHeaders).body(resource);
		}
		@Autowired
		private FollowersRepository followers;
		@Autowired
		private FollowingRepository followin;
		// 1 is following 2 let say then 2 has follower 1
		@PostMapping("follow/{id}/{followerid}")
		public ResponseEntity<String> FollowPerson(@PathVariable("id") Long id,
				@PathVariable("followerid") Long followerid)
		{
			JUser user=Urepo.findById(id).get();
			
			JUser user2=Urepo.findById(followerid).get();
			
			Followers follow=new Followers();
			Following following=new Following();
			following.setFollowingid(followerid);
			following.setUser(user);
			follow.setUser(user2);
			follow.setFollowerid(id);
			followers.save(follow);
			followin.save(following);
			
			return new ResponseEntity<String>("You are Following",HttpStatus.OK);
		}
		@GetMapping("follower/{id}")
		public ResponseEntity<List<JUser>> showUse(@PathVariable("id") Long id)
		{
			List<JUser> userlist=new ArrayList<>();
			//
			
			JUser user=Urepo.findById(id).get();
			for(Followers us : user.getFollowers())
			{ 
				JUser user1=Urepo.findById(us.getFollowerid()).get();
				userlist.add(user1);
				
			}
			
			
			System.out.println(user.getFollowers());
			return new ResponseEntity<List<JUser>>(userlist,HttpStatus.OK);
		}
		@GetMapping("following/{id}")
		public ResponseEntity<List<JUser>> showFollowing(@PathVariable("id") Long id)
		{
			List<JUser> userlist=new ArrayList<>();
			//
			
			JUser user=Urepo.findById(id).get();
			for(Following us : user.getFollowing())
			{ 
				JUser user1=Urepo.findById(us.getFollowingid()).get();
				userlist.add(user1);
				
			}
			
			
			System.out.println(user.getFollowers());
			return new ResponseEntity<List<JUser>>(userlist,HttpStatus.OK);
		}
 @GetMapping("get/all/followers")
 public ResponseEntity<List<Followers>> showAllFollowers()
 {
	 List<Followers> followere=followers.findAll();
	 return new ResponseEntity<List<Followers>>(followere,HttpStatus.OK);
 }
 @GetMapping("get/MyFollowers/{id}")
 public ResponseEntity<List<FollowerList>> getMyFollowers(@PathVariable("id") Long id)
 {
	 JUser user=Urepo.findById(id).get();
	 List<JUser> followersList=new ArrayList<>();
	 List<FollowerList> list= new ArrayList<>();
	 
	 for(Followers us:user.getFollowers())
	 {
		 
		 JUser use=Urepo.findById(us.getFollowerid()).get();
		 followersList.add(use);
		 FollowerList follow=new FollowerList();
		 follow.setId(use.getId());
		 follow.setProfilePic(use.getProfilePic());
		 follow.setUsername(use.getUsername());
		 list.add(follow);
		 
	 }
	 
	 return new ResponseEntity<List<FollowerList>>(list,HttpStatus.OK);
	 
 }
 @GetMapping("get/MyFollowing/{id}")
 public ResponseEntity<List<FollowingList>> getMyFollowing(@PathVariable("id") Long id)
 {
	 JUser user=Urepo.findById(id).get();
	 List<JUser> followersList=new ArrayList<>();
	 List<FollowingList> list= new ArrayList<>();
	 
	 for(Following us:user.getFollowing())
	 {
		 
		 JUser use=Urepo.findById(us.getFollowingid()).get();
		 followersList.add(use);
		 FollowingList follow=new FollowingList();
		 follow.setId(use.getId());
		 follow.setProfilePic(use.getProfilePic());
		 follow.setUsername(use.getUsername());
		 list.add(follow);
		 
	 }
	 
	 return new ResponseEntity<List<FollowingList>>(list,HttpStatus.OK);
	 
 }
 
 @GetMapping("/SearchByusername/{username}/{userId}")
 public ResponseEntity<SearchUser> getUserByUsername(@PathVariable("username") String username,@PathVariable("userId") Long userId)
 {
	 
	 JUser user=Urepo.findByUsername(username).get();
	 
	 SearchUser userna=new SearchUser();
	 
	 userna.setId(user.getId());
	 userna.setUsername(user.getUsername());
	 userna.setProfilePic(user.getCoverPic());
	 List<Long> foll=new ArrayList<>();
	 List<Long> followin=new ArrayList<>();
	 for(Followers folowers:user.getFollowers())
	 {
	   foll.add(folowers.getFollowerid());
	   System.out.println(folowers.getFollowerid()); 
	 }
	 for(Following follo:user.getFollowing())
	 {
		 followin.add(follo.getFollowingid());
	 }
	 userna.setFollowersCount(foll.size());
	 userna.setFollowingCount(followin.size());
	 if(foll.contains(userId))
	 {
		 
		 System.out.println("Followers is there");
		 userna.setIsfollowing("following");
		 
		 //userna.setFollowers(user.getFollowers());		 
	 }
	 else {
		 userna.setIsfollowing("Follow");	
	 }
	 return new ResponseEntity<SearchUser>(userna,HttpStatus.OK);
	   
}

@GetMapping("/find/Username/{id}/{searchingId}")
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
public ResponseEntity<?> showSingleUser(@PathVariable("id") Long id,@PathVariable("searchingId") Long searchingId)
{
	JUser user=Urepo.findById(id).get();
	
	List<Long> followers=new ArrayList<>();
	
	for(Followers follower:user.getFollowers())
	{
		followers.add(follower.getFollowerid());
	}
	if(followers.contains(searchingId))
	{
		JUser user1= new JUser();
		user1.setId(user.getId());
		user1.setStatuses(user.getStatuses());
		return new ResponseEntity<JUser>(user1,HttpStatus.OK);
	}
	else {
		
		SearchUser userna=new SearchUser();
		userna.setId(user.getId());
		 userna.setUsername(user.getUsername());
		 userna.setProfilePic(user.getCoverPic());
		 userna.setIsfollowing("Follow");
		 userna.setFollowersCount(user.getFollowers().size());
		 userna.setFollowingCount(user.getFollowing().size());
		 
		 
		 List<statusess>mj=new ArrayList<>();
		for(Statuses ss:user.getStatuses())
		{
			if(ss.getUploadedTime().before(new Date()))
			{
				ser.deleteStatuse(ss.getId());
				
			}
			statusess st=new statusess();
			
			
			st.setContent(ss.getContent());
			st.setId(ss.getId());
			st.setDescription(ss.getDescription());
			mj.add(st);
			userna.setStatuses(mj);
			
			
		}
		
		
		
		 
		 return new ResponseEntity<SearchUser>(userna,HttpStatus.OK);
		
		
	}
	
 
	 
}


@PostMapping("upload/statuses/{id}/{description}")
public ResponseEntity<String> UploadStatuses(@PathVariable("id") Long id,@RequestParam("file")
MultipartFile multipartfile,@PathVariable("description") String description) throws NotFoundException, IOException
{
	Statuses status=new Statuses();
	status.setContent(multipartfile.getBytes());
	status.setDescription(description);
	status.setUploadedTime(new Date());
    status.setUseridd(id);
	JUser user=Urepo.findById(id).get();
	status.setUser(user);
	Srepo.save(status);
	
	return new ResponseEntity<String>("Status Uploaded Successfully",HttpStatus.OK);
}
@GetMapping("/get/rma/{id}")
public ResponseEntity<List<?>> getStatuses(@PathVariable("id") Long id)
{
	//int idd=id
	List<Statuses> status=Srepo.findByUseridd(id);
	List<statusess> str=new ArrayList<>();
	
	for(Statuses st:status)
	{
		statusess statu=new statusess(st.getId(),st.getContent(),st.getDescription(),st.getUseridd());
		
		str.add(statu);
	}
	
	
	
	
	
	return new ResponseEntity<List<?>>(str,HttpStatus.OK);
}





@DeleteMapping("delete/{id}/d")
public ResponseEntity<String> deleteStatus(@PathVariable("id") Long id)
{
	Srepo.deleteById(id);
	
	return new ResponseEntity<String>("deleted",HttpStatus.OK);
	
}

@GetMapping("status/views/{id}/{viewerId}")
public StatusViews statusView(@PathVariable("id") Long id,@PathVariable("viewerId") Long viewerId)
{
	
	//Statuses status=Srepo.findById(id).get();
	StatusViews views=new StatusViews();
	
	views.setViewerid(viewerId);
	
	return Srepo.findById(id).map(book ->{
		views.setStatuses(book);
		return Strepo.save(views);
	}).orElseThrow(() -> new UserNotFoundException("not found"));
	
	
	
	
	
}

@GetMapping("/get/status/{id}")
public ResponseEntity<Statuses> getStatuseswithViews(@PathVariable("id") Long id)
{
	Statuses status=Srepo.findById(id).get();
	
	return new ResponseEntity<Statuses>(status,HttpStatus.OK);
}

@GetMapping("/get/myFolloing/Statuses/{id}")
public ResponseEntity<?> myFollowingStatuses(@PathVariable("id") Long id)
{
	JUser user=Urepo.findById(id).get();
	
	List<Long> following=new ArrayList<>();
	
	for(Following foll:user.getFollowing())
	{
		following.add(foll.getFollowingid());
	}
	
	
	List<Statuses> stt=Srepo.findAllById(following);
	
	
	List<statusess> str=new ArrayList<>();
	for(Statuses st:stt)
		
	{
		
		statusess stss=new statusess();
		stss.setId(st.getId());
		stss.setDescription(st.getDescription());
		stss.setUseridd(st.getUseridd());
		str.add(stss);
		
	}
	System.out.println(str);
	
	return new ResponseEntity<List<?>>(str,HttpStatus.OK);
}

		@GetMapping("/getStatuses/{id}")
		public ResponseEntity<?>getStatuseOfIn(@PathVariable("id") Long id)
		{
			JUser user=Urepo.findById(id).get();
			
			List<Long> followingid=new ArrayList<>();
			for(Following followi:user.getFollowing())
			{
				followingid.add(followi.getFollowingid());
			}
			List<s> StatusId=new ArrayList<>();
			
			
			return new ResponseEntity<List<?>>(StatusId,HttpStatus.OK);
			
			
			
 		}
		
		@GetMapping("/getStatuses/perso/{id}")
		public ResponseEntity<?> getStatusesofSinglePerson(@PathVariable("id") Long id)
		{
			
			JUser user=Urepo.findById(id).get();
			List<statusess> status=new ArrayList<>();
			List<Long> on=new ArrayList<>();
			for(Following following:user.getFollowing())
			{
				System.out.println(following.getFollowingid());
				on.add(following.getFollowingid());
				JUser user2=Urepo.findById(following.getFollowingid()).get();
				statusess statuses=new statusess();
				statuses.setId(user2.getId());
				statuses.setStatuses(user2.getStatuses());
				statuses.setContent(user2.getProfilePic());
				status.add(statuses);
			}
			System.out.println(on);			
			return new ResponseEntity<List<?>>(status,HttpStatus.OK);
		}
		
		@PostMapping("/post/Story/{id}")
		public ResponseEntity<?> PostAStory(@PathVariable("id") Long id,@RequestParam("file") MultipartFile multipartfile) throws IOException
		{
			Story story=new Story();
			List<Story> stry=new ArrayList<>();
			story.setStory(multipartfile.getBytes());
			story.setUserId(id);
			Ssrepo.save(story);
			stry.add(story);
					
		JUser user=Urepo.findById(id).get();
		
		user.setStory(stry);
		Urepo.save(user);
		return new ResponseEntity<JUser>(user,HttpStatus.OK);
		}
	@GetMapping("/thread")
	public List<JUser> getThread() throws Exception
	{
		ExecutorService exservice=Executors.newFixedThreadPool(10);
		
		List<Future<JUser>>list=new ArrayList<Future<JUser>>();
		
		System.out.println("Started time"+System.currentTimeMillis()/1000);
		List<JUser>li=new ArrayList<>();
		for(int i=1;i<=5;i++)
		{
		
			Future<JUser> future=(Future<JUser>) exservice.submit(callable);
			list.add(future);
			li.add(callable.call());
		}
		for(Future<JUser> fut:list)
		{
			fut.get();
		} 
		exservice.shutdown();
		System.out.println("End Time time"+System.currentTimeMillis()/1000);
		
		
		return li;
		
	}
	@GetMapping("/fe")
	public JUser etInt() throws InterruptedException, ExecutionException
	{
		ExecutorService exservice=Executors.newFixedThreadPool(10);
		TestCallble test=new TestCallble(8);
		Future<JUser> future=exservice.submit(test);
		exservice.shutdown();
		return future.get();
		
	}
	@GetMapping("/getHello")
	public List<String> printNEw() throws Exception
	{
		ExecutorService exservice=Executors.newFixedThreadPool(10);
		List<Future<String>>list=new ArrayList<Future<String>>();
		List<String> str=new ArrayList<>();
		for(int i=0;i<=5;i++)
		{
			Future<String> future=exservice.submit(Strac);
			list.add(future);
			str.add(Strac.call());			
		}
		for(Future<String> fut:list)
		{
			fut.get();
		}
		exservice.shutdown();
		return str;
	}
	@Autowired
	private Rest rest;
	@GetMapping("/det")
	public List<JUser> calUjse() throws InterruptedException, ExecutionException {
		ExecutorService exservice=Executors.newFixedThreadPool(10);
		List<JUser>list=new ArrayList<>();
		Rest test=new Rest(8);
		Future<JUser> user=exservice.submit(test);
		Future<JUser> user2=exservice.submit(new Rest(2));
		
		
		list.add(user.get());
		list.add(user2.get());
		return list;
		
	}
	@GetMapping("/cal")
	public List<Integer> calculateInput() throws InterruptedException, ExecutionException
	{
		SquareCalculator squareCalculator = new SquareCalculator();
		Future<?> future = squareCalculator.Calculate(10);
		Future<?> future1 = squareCalculator.Calculate(100);
		Future<?> future2 = squareCalculator.Calculate(1000);
		Future<?> future3 = squareCalculator.Calculate(1000);
		Future<?> future4 = squareCalculator.Calculate(1000);

		while(!future.isDone() && future1.isDone() && !future2.isDone()) {
		    System.out.println("Calculating...");
		    
		    	    Thread.sleep(300);
		}
 
		 
		List<Integer> result =new ArrayList<>();
				result.add((Integer) future.get());
				result.add((Integer) future1.get());	
				result.add((Integer) future2.get());
				Integer result1 = (Integer) future1.get();
				Integer result2 = (Integer) future2.get();
				Integer result3 = (Integer) future3.get();
				Integer result4 = (Integer) future4.get();
				System.out.println(
					      String.format(
					        "future1 is %s and future2 is %s", 
					        future1.isDone() ? "done" : "not done", 
					        future2.isDone() ? "done" : "not done"
					      )
					    );
System.out.println(result1 +  " and " + result2);

Integer tot=result1+result2+result3+result4;
System.out.println(tot); 
		return result;
		
	}
	
	
	
	@RequestMapping("/process")
    public String process(){
         
        String msg = "";
        List<Future<String>> futureList = new ArrayList<>();
        for(int threadNumber = 0; threadNumber < 5; threadNumber ++){
            CallableWorker callableTask = new CallableWorker(String.valueOf(threadNumber));
        
			Future<String> result = threadPool.submit(callableTask);
            futureList.add(result);
        }
         
        for(Future<String> future: futureList){
            try {
                msg += future.get() + "#####";
            } catch (Exception e){}
        }
         
        return msg;
	}
	@GetMapping(value = "/testCallable")
    public Callable<JUser> echoHelloWorld() 
    {
          return () -> 
          {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
                 
                return Urepo.findById((long)8).get();
          };
    }
	
	@RequestMapping ("/bus/{id}")
    public @ResponseBody List<String> getAllCars(@PathVariable("id") Long id) throws InterruptedException, ExecutionException {
//		CompletableFuture<Boolean> boolean1= carservice.veryLongMethod();
//        CompletableFuture<Boolean> boolean2= carservice.veryLongMethod();
//        CompletableFuture<Boolean> boolean3= carservice.veryLongMethod();
//		
//        CompletableFuture.allOf(boolean1,boolean2,boolean3).join();
        List<String>list=new ArrayList<>();
        CompletableFuture<String> email2= carservice.getAllCars((long)9);
        CompletableFuture<String> email3= carservice.getAllCars((long)8);
        CompletableFuture<String> email4= carservice.getAllCars(id);
        CompletableFuture.allOf(email2,email3,email4).join();
        list.add(email2.get());
        list.add(email3.get());
        list.add(email4.get());
        return list;
		
							
			
    }
	@GetMapping("/getPe")
public JUser getUserd() throws InterruptedException, ExecutionException, TimeoutException
{
		JUser user=carservice.finduser((long)2);
		
	return user;
	
}

@PostMapping("/post/rest")
public Tert saveTert() throws InterruptedException, ExecutionException
{
	
	Tert tert=new Tert();
	tert.setName("ramsai");
	SvaeCallable callable=new SvaeCallable(tert);
	ExecutorService exservice=Executors.newFixedThreadPool(10);
	Future<Tert> tertw=exservice.submit(callable);
	return tertw.get();
	
}
@Autowired
private UserServiceComact serviced;
@PostMapping("/saveTerto")
public ResponseEntity saveTerto(@RequestParam("file") MultipartFile[] multipartfile) throws Exception
{
	for(MultipartFile file:multipartfile)
	{
		serviced.saveTert(file);
	}
	return  ResponseEntity.status(HttpStatus.CREATED).build();
}
@GetMapping("/getAllTero")
public CompletableFuture<ResponseEntity> getAllTero()
{
	return serviced.getAllTero().thenApply(ResponseEntity::ok);
	
}
@GetMapping("/getAllTeron")
public CompletableFuture<ResponseEntity> getAllTerom()
{
	return serviced.getAllTeron().thenApply(ResponseEntity::ok);
	
}
@GetMapping("/getAllTer")
public ResponseEntity getAllTeros()
{
	CompletableFuture<List<Tert>> future=serviced.getAllTero();
	CompletableFuture<List<Tert>> future2=serviced.getAllTero();
	CompletableFuture<List<Tert>> future3=serviced.getAllTero();
	CompletableFuture.allOf(future,future2,future3);
	
	return ResponseEntity.status(HttpStatus.OK).build();
	
}
@GetMapping("/getUserBySelectedIds")

public ResponseEntity getAllSelectedUsers() throws InterruptedException, ExecutionException
{
	List<Long>id=new ArrayList<>();
	id.add((long)1);
	id.add((long)2);
	id.add((long)8);
	id.add((long)4);
	
	CompletableFuture<Boolean> future=serviced.getSelectedUsers((long)1);
	CompletableFuture<Boolean> future1=serviced.getSelectedUsers((long)2);
	CompletableFuture<Boolean> future2=serviced.getSelectedUsers((long)4);
	CompletableFuture<Boolean> future3=serviced.getSelectedUsers((long)8);
	CompletableFuture.allOf(future,future1,future2,future3).join();
	
	return  ResponseEntity.status(HttpStatus.OK).build();
}
@GetMapping("/testingP")
public ResponseEntity getAllCa() throws InterruptedException, ExecutionException {
CompletableFuture<Boolean> boolean1= carservice.veryLongMethod();
   CompletableFuture<Boolean> boolean2= carservice.veryLongMethod();
   CompletableFuture<Boolean> boolean3= carservice.veryLongMethod();
   CompletableFuture<Boolean> boolean4= carservice.veryLongMethod();
	
  CompletableFuture.allOf(boolean1,boolean2,boolean3,boolean4).join();
  
  return ResponseEntity.status(HttpStatus.OK).build();
}

@GetMapping("/createFolder/{filename}")
public ResponseEntity<String> createFolder(@PathVariable("filename") String filename) throws IOException
{
	File file=new File("C:\\Users\\Ramsa\\Downloads\\"+filename);
	
    boolean created=file.mkdirs();
    if(created)
    {
    	if(file.exists())
    	{
    		System.out.println("directory name"+file.getCanonicalPath());
    		return new ResponseEntity<String>("file is Already exist",HttpStatus.OK);
    	}
    	else {
    	File newfile=new File("C:\\Users\\Ramsa\\Downloads\\"+filename+"\\"+"ramsai.txt");
    	newfile.createNewFile();
    	return new ResponseEntity<String>("file is Created",HttpStatus.OK);
    	}
    }
    else {
    	if(file.exists())
    	{
    		System.out.println("directory name"+file.getCanonicalPath());
    		File newfile=new File("C:\\Users\\Ramsa\\Downloads\\"+filename+"\\"+"ramsaiprasad.txt");
        	newfile.createNewFile();
    		return new ResponseEntity<String>("file is created",HttpStatus.OK);
    	}
    	else {
    		  return new ResponseEntity<String>("PLease create Folder",HttpStatus.OK);
    	}
    }
  	
}

//@GetMapping("pickaFile/{filename}")
//public ResponseEntity<?> getListOfFiles(@PathVariable("filename") String filename) throws IOException
//{
//	File file=new File("C:\\users\\ramsa\\Downloads");
//	
//
//	File filelist[]=file.listFiles();
//	List<File> filel=new ArrayList<>();
//	List<byte[]> contetn=new ArrayList<>();
//	for(File filedaa:filelist)
//	{
//		LOGGER.info(filedaa.getName());
//		LOGGER.info(filedaa.getTotalSpace());
//		filel.add(filedaa);
//	}
	
	//return getcontentOfFile(filename,"C:\\Users\\Ramsa\\Downloads\\");
	
//}
@GetMapping("/getonFile/{filename}")
public ResponseEntity<Resource> getcontentOfFile(@PathVariable("filename") String filename) throws IOException
{
	
   String DIRECTORY3="C:\\users\\ramsa\\downloads";
	Path filePath=Paths.get(DIRECTORY3).toAbsolutePath().normalize().resolve(filename);

	if(!Files.exists(filePath))
	{
		throw new FileNotFoundException(filename+"file Not Found");
	}
	Resource resource=new UrlResource(filePath.toUri());
	HttpHeaders httpHeaders=new HttpHeaders();
	httpHeaders.add("filename",filename);
	String headerKey="content-Dispostion";
	String headerValue="attachment;filename="+resource.getFilename();
	
	httpHeaders.add(headerKey,headerValue);
	
	return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
			  .headers(httpHeaders).body(resource);
	
}
@GetMapping("/get/testData")
public ResponseEntity<?> getPrimitiveData()
{
	String s="10";
	int a=0;
	
	for(int i=0;i<=4;i++)
	{
		a=a+Integer.parseInt(s);
		System.out.println(a);
	}
	return new ResponseEntity<Integer>(a,HttpStatus.OK);
}
@Autowired
private TokenSecurityRepository Tsrepo;
@GetMapping("VerifySession/{sessionToken}/{userId}")
public ResponseEntity<?> VerifySessionToken(@PathVariable("sessionToken") String sessionToken,@PathVariable("userId") Long userId )
{
	String SToken=RandomString.make(45);
	TokenSecurity security=new TokenSecurity();
	security.setSessionCreatedTime(new Date());
	security.setSessionToken(SToken);
	security.setUserId(userId);
	Tsrepo.saveAndFlush(security);
	
	return new ResponseEntity<String>(SToken,HttpStatus.OK);
}
@Autowired
private BlogService blogService;
@Autowired
private BlogDao Dao;
@GetMapping("/titleCount")
public List<UserRequest> getTotalBlogs(){
	int id=1;
    return blogService.getTotalBlogs(id);
}
@GetMapping("/getstores/{id}/{Title}")
public ResponseEntity<?> getDataFromStoresProcedure(@PathVariable("id") int id,@PathVariable("Title") String Title)
{
    //List<Object[]> list=Dao.getAllBlogs(id,Title);
    Map<String,Object[]> li=new HashMap<>();
    List<Object> obj=new ArrayList<>();
//    for(Object[] ob:list)
//    {
//    	li.put("1", ob);
//    	
//    	
//    }
//    Map<Object,Object> lin=new HashMap<>();
//  
//    for(Object og:li.get("1"))
//    {	
//    	obj.add(og);
//    	LOGGER.info(obj.size());
//    	
//    }
//    for(int i=0;i<obj.size();i++)
//    {
//       System.out.println(i);
//       lin.put(obj.get(i), obj.get(i));
//    }
//   
//    System.out.println(obj.size());
    return new ResponseEntity<>(Dao.getAllBlogs(id,Title),HttpStatus.OK);
	
}
@Autowired
private StoredProcedureService SPS;
@GetMapping("/getJdbc")
public ResponseEntity<?> getJDBC()
{
	
	List<NewUsers> list=SPS.getallUsersNew();
	
	return new ResponseEntity<>(list,HttpStatus.OK);
}
@GetMapping("/getOnem/{id}")
public ResponseEntity<?> getOneOf(@PathVariable("id") int id)
{
	List<Object> lis=SPS.getOneUser();
	
	return new ResponseEntity<>(lis,HttpStatus.OK);
}
@PostMapping("/saveJdbc")
public ResponseEntity<?> saveJDBCTemp(@RequestBody NewUsers users)
{
	int user=SPS.saveNewUser(users);
	
	return new ResponseEntity<>(user,HttpStatus.OK);
}
@GetMapping("/getAllList")
public ResponseEntity<?> gettem()
{
	
	return new ResponseEntity<>(SPS.getAllNewUsers(),HttpStatus.OK);
	
	
}
@GetMapping("/getSingleTertDetails/{id}")
public CompletableFuture<Object> getSingleTertDetails(@PathVariable("id") Long id)
{
	//Object object=serviced.getOneTertById(id).thenApply(ResponseEntity::ok);
	//System.out.println(object);
	//List<Object> listOfObject=new ArrayList<>();
	CompletableFuture<Object> object=serviced.getOneTertById(id).thenApply(ResponseEntity::ok);;
	//listOfObject.add(object);
	//Object rest=serviced.getOneTertById((long)200);
	//listOfObject.add(rest);	
	return object ;
}
@Autowired
private TertRepository RT;
@GetMapping("/datatobeSupply/{id}")
public ResponseEntity<?> getDataforDataMa(@PathVariable("id") Long id)
{
	Object obj=RT.findById(id);
	return new ResponseEntity<>(obj,HttpStatus.OK);
}
@GetMapping("/getCallableTert")
public ResponseEntity<?> getTertoByCallable()
{
    ExecutorService executorService= Executors.newFixedThreadPool(10);
    DataManipulation mani=new DataManipulation();
    Future<Tert> obj=executorService.submit(mani);
    try {
    return new ResponseEntity<>(obj.get(),HttpStatus.OK);
    }
    catch(Exception e)
    {
    	return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
    }
}

@GetMapping("/getCallableTer")
public ResponseEntity<?> getTertoByCalable()
{
    ExecutorService executorService= Executors.newFixedThreadPool(10);
    //DataManipulation mani=new DataManipulation();
    ExtendedeCal ext=new ExtendedeCal();
    Future<Object> obj=executorService.submit(ext);
    try {
    return new ResponseEntity<>(obj.get(),HttpStatus.OK);
    }
    catch(Exception e)
    {
    	return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
    }
}
@RequestMapping(value="/sendRequestToFollower/{requestHasTorecieverId}/{requestsenderId}",method=RequestMethod.POST)
public ResponseEntity<?> SaveRequestSentbytheFollower(@RequestBody FollowerRequest followerRequest,
		@PathVariable("requestHasTorecieverId") Long requestHasTorecieverId,
		@PathVariable("requestsenderId") Long requestsenderId)
{
	FRS.noteFollowerRequest(followerRequest,requestHasTorecieverId,requestsenderId);
	return new ResponseEntity<String>("You Request To follow",HttpStatus.OK);
}
@PostMapping("/display/{name}")
public ResponseEntity<?> displayMessage(@PathVariable("name") String name)
{
	return new ResponseEntity<>(name,HttpStatus.OK);
}
@GetMapping("/GetMyrequests/{id}")
public ResponseEntity<?> getAllMyRequests(@PathVariable("id") Long id)
{
	List<FollowerRequest> data=FRS.GetFollowerRequestsByID(id);
	
	return new ResponseEntity<>(data,HttpStatus.OK);
}
@Autowired
private FollowerRequestRepository FRR;
@GetMapping("/approveById/requestId/{id}/{requestType}")
public ResponseEntity<?> ApproveRequest(@PathVariable("id") Long id,@PathVariable("requestType") String requestType)
{
	
	if(requestType.equalsIgnoreCase("Accept"))
	{
	   return new ResponseEntity<String>("You Accepted Request",HttpStatus.OK);
	}
	else if(requestType.equalsIgnoreCase("Reject"))
	   {
		
		  return new ResponseEntity<String>("you Rejected The Request",HttpStatus.OK);
	    }
	else {
		    return new ResponseEntity<String>("something Went Wrong",HttpStatus.OK);
	}
	
}
@GetMapping("/getMyRequestsSentToApprove/{id}")
public ResponseEntity<?> getAllMyRequestsSendToApprove(@PathVariable("id") Long id)
{
	
	return new ResponseEntity<>(FRS.getMyListOfRequests(id),HttpStatus.OK);
}
@GetMapping("/ApproveRequests/{requestId}/{currentUserId}/{requestSenderId}/{requestType}")
public ResponseEntity<?> ApproveRequestOfThePending(@PathVariable("requestId") Long requestId,
		@PathVariable("currentUserId") Long currentUserId,@PathVariable("requestSenderId") Long requestSenderId,
		@PathVariable("requestType") String requestType)
{
	FRS.approveRequestAndAddToFollowerList(requestId,currentUserId,requestSenderId, requestType);
	return new ResponseEntity<>("You are now Accepted",HttpStatus.OK);
	
}
@GetMapping("/getMyListOfPendingRequets/{id}")
public ResponseEntity<?> ListOfMyPendingRequests(@PathVariable("id") Long id){
	
	
	return new ResponseEntity<>(FRS.GetFollowerRequestsByID(id),HttpStatus.OK);
}
@GetMapping("/CreateTable/{tablename}")
public ResponseEntity<?> CreateTableWithTableGivenByUser(@PathVariable("tablename") String tablename)
{
	FRS.CreateTableDynamically(tablename);
	
	return new ResponseEntity<>("Table is Created Successfully",HttpStatus.OK);
}
@GetMapping("/ExecuteCommand/{command}")
public ResponseEntity<?> ExecuteCommandOfTheUser(@PathVariable("command") String command)
{
	FRS.TakeCommandsFromUser(command);
	
	return new ResponseEntity<>("Your Command Executed successfully",HttpStatus.OK);
}
@GetMapping("/getDataByQueries/{query}")
public ResponseEntity<?> getAllDataForCalculation(@PathVariable("query") String query)
{
	return new ResponseEntity<>(FRS.getDataByQueries(query),HttpStatus.OK);

}
@GetMapping("/getApiIntegration")
public ResponseEntity<?> GetApiData()
{
	return new ResponseEntity<>(FRS.APIIntegration(),HttpStatus.OK);
}


@PostMapping("/testingDatawithPost")
public ResponseEntity<?> GetTestData(@RequestBody ResponeseforTesting resn)
{
	ResponeseforTesting resq=new ResponeseforTesting();
	List<ExceptionMessages> ex=new ArrayList<ExceptionMessages>();
	List<String> newError= new ArrayList<>();
	if(resn.getAddress().length() == 10)
	{
	  resq.setAddress(resn.getAddress());
	  
	}
	else {
		
		newError.add("Value is not Correct");
		return new ResponseEntity<List<String>>(newError,HttpStatus.NOT_FOUND);
	}
	 if (resn.getName().length()==5)
	{
		resq.setName(resn.getName());
	}
	 else {
		 newError.add("Value is not dorrect");
			return new ResponseEntity<List<String>>(newError,HttpStatus.NOT_FOUND);
	 }
	
	resq.setName(resn.getName());
   return new ResponseEntity<ResponeseforTesting>(resq,HttpStatus.OK);	
}

@GetMapping("/getListOfIndexesList/{TableName}")
public ResponseEntity<?> getListOfIndexes(@PathVariable("TableName") String TableName)
{
	List<Object> list=LSP.getAllIndexData(TableName);
	
	return new ResponseEntity<List<Object>>(list,HttpStatus.OK);
}

@GetMapping("/getListOfTableNames")
public ResponseEntity<?> getListOfTableNames()
{
	List<TableNames> list=LSP.getAllTableNames();
	
	return new ResponseEntity<List<TableNames>>(list,HttpStatus.OK);
}
@PostMapping("saveAllTheCustomVariables")
public ResponseEntity<?> SaveAllTheVariable(@RequestBody List<CustomVariables>  customVariables)
{
	List<CustomVariables> cust=new ArrayList<>();
	//cust.add(customVariables.get(1));
	
for(CustomVariables custom:customVariables)
   {		CustomVariables csr=new CustomVariables();
	  csr.setCustom_Variable(custom.getCustom_Variable());
	  cust.add(csr);	  
		
	}
CVR.saveAll(cust);
	
	return new ResponseEntity<List<CustomVariables>>(cust,HttpStatus.OK);
	
}

@PostMapping("SearchById")
public ResponseEntity<?> SearchByID(@RequestParam(required=true) Long Id)
{
	JUser users=Urepo.findById(Id).get();
	return new ResponseEntity<JUser>(users,HttpStatus.OK);
}

@GetMapping("GetValueFromFuncTionInSQL")
public ResponseEntity<?> GetValueOfFunc()
{
	List<String> string=LSP.GetTheValueFromFunction();
	return new ResponseEntity<List<String>>(string,HttpStatus.OK);
}
@PostMapping("GetFileData")
public ResponseEntity<?> GetData()
{
	
	String file="C:\\Users\\Ramsa\\Documents\\course.json";
	JSONParser parser=new JSONParser();
	List<Object> list=new ArrayList<>();
	try {
		
		Object obj=parser.parse(new FileReader(file));
		JSONObject jsonObject=(JSONObject) obj;
		Class<? extends JSONObject> name=jsonObject.getClass();
		
		list.add(name);
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return new ResponseEntity<Object>(list,HttpStatus.OK);
}
@SuppressWarnings("unchecked")
@GetMapping("GetUrlName")
public ResponseEntity<?> NameOfUrl(HttpServletRequest request)
{
	String url=request.getRequestURL().toString();
	String siteURl=url.replace(request.getServletPath(),"");
	List<String> list=new ArrayList<>();
	list.add(request.getAuthType());
	list.add(request.getContextPath());
	System.out.println(request.getSession());
	System.out.println(request.getSession());
	return new  ResponseEntity<List<String>>(list,HttpStatus.OK);
}

@GetMapping("/sendDataToAnotherRequest/{userName}")
public ResponseEntity<?> sendUserData(@PathVariable("userName") String userName)
{
	
	JUser user=Urepo.findByUsername(userName).get();
	return new ResponseEntity<JUser>(user,HttpStatus.OK);
}
@GetMapping("/getLockData/{username}")
public ResponseEntity<?> GetDataOfLock(HttpServletRequest request,@PathVariable("username") String username)
{
	AccountLock lock=FRp.findByUsername(username).get();
	 return new ResponseEntity<AccountLock>(lock,HttpStatus.OK);
}
}