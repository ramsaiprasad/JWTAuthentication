package net.codejava.StoredProcedures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class ListOfIndexesProcedure {
	private final JdbcTemplate jdbcTemplate;

	public ListOfIndexesProcedure(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<TableNames> getAllTableNames()
	{
		
		return jdbcTemplate.query("EXEC TABLE_NAMES_LIST", new RowMapper<TableNames>() {
	        public TableNames mapRow(ResultSet resultSet, int i) throws SQLException {
	        	TableNames Tnames=new TableNames();
	        	Tnames.setName(resultSet.getString(1));
	        	Tnames.setTotalSpaceKB(resultSet.getInt(2));
	        	Tnames.setUnusedSpaceKB(resultSet.getInt(3));
	            return Tnames;
	        }
	    });
	}
	
	public List<String> GetTheValueFromFunction()
	{
		return jdbcTemplate.query("SELECT [dbo].EXAMPLE()", new RowMapper<String>() {
			public String mapRow(ResultSet resultSet,int i) throws SQLException{
				String Id=resultSet.getString(1);
				return Id;
			}
		});
	}
	
	public List<Object> getAllIndexData( String name)
	{
		List<String> table=new ArrayList<>();
		table.add("INDEX_NAME");
		table.add("INDEX_TYPE");
		table.add("IS_UNIQUE");
		table.add("TABLE_NAME");
		table.add("COLUMN_NAME");
		table.add("IS_INCLUDED_COLUMN");
		table.add("KEY_ORDINAL");
		
		
		
		return jdbcTemplate.query("EXEC LIST_OF_INDEXES_FOR_THE_TABLE ?",
				new PreparedStatementSetter(){

			
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						// TODO Auto-generated method stub
										  
						   ps.setString(1, name);
					}
	},
				new RowMapper<Object>() {
		//List<Object> obj=new ArrayList<>();
		String nf="CLUSTERED";
		public Object  mapRow(ResultSet resultSet, int i) throws SQLException {
        	Map<String,Object> obj=new HashMap<>();
//        	NewUsers userDto = new NewUsers();
//            userDto.setBlogId(resultSet.getInt(1));
//            userDto.setBlogTitle(resultSet.getString(2));;
//            userDto.setYearOfPost(resultSet.getInt(3));
        	obj.put(table.get(0), resultSet.getObject(1));
        	if(resultSet.getObject(2).equals(nf))
        	{
        	  obj.put("INDEX_TYPE", resultSet.getObject(2));
        	}
        	else {
        		obj.put("INDEX_TYPE","BAD");
        	}
        	obj.put("IS_UNIQUE", resultSet.getObject(3));
        	obj.put("TABLE_NAME", resultSet.getObject(4));
        	obj.put("COLUMN_NAME", resultSet.getObject(5));
        	obj.put("IS_INCLUDED_COLUMN", resultSet.getObject(7));
        	obj.put("KEY_ORDINAL", resultSet.getObject(7));
        	
        	return obj;
            
        }
	});
	
	}
}
