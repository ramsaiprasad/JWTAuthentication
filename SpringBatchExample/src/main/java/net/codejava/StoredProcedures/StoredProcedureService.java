package net.codejava.StoredProcedures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class StoredProcedureService {
	private final JdbcTemplate jdbcTemplate;
	public StoredProcedureService(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate =jdbcTemplate ;
	}
	
	public List<NewUsers> getallUsersNew()
	{
		return jdbcTemplate.query("SELECT * FROM BLOG", new RowMapper<NewUsers>() {
	        public NewUsers mapRow(ResultSet resultSet, int i) throws SQLException {
	        	NewUsers userDto = new NewUsers();
	            userDto.setBlogId(resultSet.getInt(1));
	            userDto.setBlogTitle(resultSet.getString(2));;
	            userDto.setYearOfPost(resultSet.getInt(3));
	            return userDto;
	        }
	    });
		
	

}
	public List<Object> getOneUser()
	{
		//int id=1;
		List<Integer> ids=new ArrayList<>();
		ids.add(1);
		ids.add(2);
		
		return jdbcTemplate.query("EXEC getAllBlogs ?",
				new PreparedStatementSetter(){

			
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						// TODO Auto-generated method stub
						
						  ps.setInt(1, 1);
						   //ps.setString(2, "ramsai");
					}
			
		}
				
				,new RowMapper<Object>() {
	        /**
	         *
	         */
					List<Object>ne=new ArrayList<>();
	        public Object  mapRow(ResultSet resultSet, int i) throws SQLException {
	        	Map<String,Object> obj=new HashMap<>();
//	        	NewUsers userDto = new NewUsers();
//	            userDto.setBlogId(resultSet.getInt(1));
//	            userDto.setBlogTitle(resultSet.getString(2));;
//	            userDto.setYearOfPost(resultSet.getInt(3));
	        	obj.put("ID", resultSet.getObject(1));
	        	obj.put(resultSet.getObject(2).toString(), resultSet.getObject(2));
	        	obj.put(resultSet.getObject(3).toString(), resultSet.getObject(3));
	        	
	        	return obj;
	            
	        }
	    });
	}
	
	
	public int saveNewUser(NewUsers N)
	{
		String query = "INSERT INTO BLOG VALUES('"+N.getBlogId()+"','"+N.getBlogTitle()+"','"+N.getYearOfPost()+"')";
		return jdbcTemplate.update(query);
	
	}
	
	public List<Object> getAllNewUsers(){
		return jdbcTemplate.query("SELECT * FROM BLOG",new ResultSetExtractor<List<Object>>() {

			@Override
			public List<Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<Object> list=new ArrayList<>();
				
				while(rs.next())
				{
				 Map<String,Object> ne=new HashMap<>();
				  ne.put("Id", rs.getObject(1));
				 
				  ne.put("Title", rs.getObject(2));
				  ne.put("Year", rs.getObject(3));
				  ne.put("SessionID",1729);
				  list.add(ne);
				  
				}
				return list;
			}
			
		});
	}
	
	//SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
    //        .withProcedureName("READ_EMPLOYEE");
	
}
