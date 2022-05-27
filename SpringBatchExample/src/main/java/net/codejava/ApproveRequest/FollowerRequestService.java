package net.codejava.ApproveRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.codejava.UserNotFoundException;
import net.codejava.Repository.FollowersRepository;
import net.codejava.Repository.FollowingRepository;
import net.codejava.Repository.JUserRepository;
import net.codejava.model.Followers;
import net.codejava.model.Following;
import net.codejava.model.JUser;

@Service
public class FollowerRequestService {
	@Autowired
	private FollowerRequestRepository Frrepo;
	@Autowired
	private JUserRepository Urepo;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private FollowerRequestRepository FRR;
	@Autowired
	private FollowersRepository Followers;
	@Autowired
	private FollowingRepository Following;
	@Autowired
	private MyRequestTableRepository MYR;
	
	
	
	public FollowerRequest noteFollowerRequest(FollowerRequest followerRequest,Long requestHasTorecieverId,Long requestsenderId)
	{
		FollowerRequest request=new FollowerRequest();
		MyrequetsTable mytable=new MyrequetsTable();
		
		mytable.setRequest(followerRequest.getRequest());
		mytable.setRequestSentTo(requestHasTorecieverId);
		mytable.setRequestType(followerRequest.getRequestType());
		
		request.setRequest(followerRequest.getRequest());
		request.setRequestSender(followerRequest.getRequestSender());
		request.setRequestType(followerRequest.getRequestType());
		/*
		 * follower request is here request body and 
		 */
		JUser user= Urepo.findById(requestsenderId).get();
		mytable.setUser(user);
		
		MYR.save(mytable);
		
		return Urepo.findById(requestHasTorecieverId).map(book ->{
			request.setUser(book);
			return Frrepo.save(request);
		}).orElseThrow(() -> new UserNotFoundException("not found"));
	}
	                                                                 //Who is accepting the Request...
	     //userId is request Which was sent by the person to follow
	//requestType means weather it is accepted or Rejected
	public ResponseEntity<String> approveRequestAndAddToFollowerList(Long requestId,Long Currentuserid,Long requestSenderID,String requestType)
	{   
		JUser user=Urepo.findById(Currentuserid).get();
		
		JUser user2=Urepo.findById(requestSenderID).get();
		
		FollowerRequest requests=FRR.findById(requestId).get();
		
		Followers follower=new Followers();
		Following following=new Following();
		follower.setFollowerid(requestSenderID);
		follower.setUser(user);
		following.setFollowingid(Currentuserid);
		following.setUser(user2);
		Followers.save(follower);
		Following.save(following);
		requests.setRequestType(requestType);
		FRR.save(requests);
		
		return new ResponseEntity<String>("You have a follower now",HttpStatus.OK);
		
	}
	public Map<String, Object> getAllMyRequests(Long id)
	{
		JUser requests=Urepo.findById(id).get();
		Map<String,Object> requestMap=new HashMap<>();
		requestMap.put("requests", requests.getFollowersrequest());
		
		return requestMap;
	}
	
	public List<FollowerRequest> GetFollowerRequestsByID(Long id)
	{
		return jdbcTemplate.query("SELECT * FROM follower_request where user_id=? and request_type=?",
				new PreparedStatementSetter(){

			
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						// TODO Auto-generated method stub
						
						  ps.setLong(1, id);
						  ps.setString(2, "Requested");
						  
					}
			
		}
				
				,new RowMapper<FollowerRequest>() {
	        /**
	         *
	         */
					//List<FollowerRequest>ne=new ArrayList<>();
	        public FollowerRequest  mapRow(ResultSet resultSet, int i) throws SQLException {
	        	FollowerRequest req=new FollowerRequest();
	        	req.setId(resultSet.getLong(1));
	        	req.setRequest(resultSet.getString(2));
				return req;
	            
	        }
	    });
	}
	
public List<MyrequetsTable> getMyListOfRequests(Long id)
{
     return jdbcTemplate.query("SELECT * FROM myrequesttable where user_id=? and request_type=?", new PreparedStatementSetter() {

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			 ps.setLong(1, id);
			 ps.setString(2, "Requested");
			
		}
     },new RowMapper<MyrequetsTable>() {
			public MyrequetsTable mapRow(ResultSet result,int i) throws SQLException {
				MyrequetsTable reqTable=new MyrequetsTable();
				reqTable.setId(result.getLong(1));
				reqTable.setRequest(result.getString(2));
				reqTable.setRequestSentTo(result.getLong(3));
				reqTable.setRequestType(result.getString(4));
	
				return reqTable;
				
			}
		}
    	 
     );
}
public ResponseEntity<?> CreateTableDynamically(String tablename)
{
	
	String sd="id";
	String df="bigint";
	String sf="address";
	String sdf="Varchar(50)";
	String sql="Create Table"+" "+tablename+" "+"("+sd+" "+df+","+sf+" "+sdf+","+")";
	jdbcTemplate.execute(sql);
	
	return new ResponseEntity<>("Table is Created Successfully",HttpStatus.OK);
	
}
public ResponseEntity<?> TakeCommandsFromUser(String command)
{
	jdbcTemplate.execute(command);
	return new ResponseEntity<>("You command Executed Successfulle",HttpStatus.OK);
}

public List<Object> getDataByQueries(String querys)
{
	
	
	return jdbcTemplate.query(querys,new ResultSetExtractor<List<Object>>() {

		@Override
		public List<Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
			// TODO Auto-generated method stub
			//
			List<Object> objectlist=new ArrayList<>();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			System.out.println(rsMetaData);
			System.out.println(rsMetaData.getColumnCount());
			while(rs.next())
			{
		
				
			 Map<Integer,Object> ne=new HashMap<>();
			 List<Object> list=new ArrayList<>();
			 for(int i=1;i<=rsMetaData.getColumnCount();i++)
			 {
			   //ne.put(i, rs.getObject(i));
				 //objectlist.add(rs.getObject(i));
				 list.add(rs.getObject(i));
			    //ne.put("Title", rs.getObject(i));
			 //ne.put("Year", rs.getObject(3));
			  
			   // ne.put(,1729);
			   // list.add(ne);
				 
			 }
			 objectlist.add(list);
			 
			}
			return objectlist;
			
		}
	}
			);
}


public ResponseEntity<?> APIIntegration()
{
	RestTemplate template=new RestTemplate();
	String url="http://localhost:8090/api/test/getDataByQueries/SELECT * FROM JUSERS";
	Object[] str=template.getForObject(url,Object[].class);
	
	
	return new ResponseEntity<>(str,HttpStatus.OK);
	
}
}
