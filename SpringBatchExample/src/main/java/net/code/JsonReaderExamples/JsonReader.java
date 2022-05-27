package net.code.JsonReaderExamples;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




public class JsonReader {
	
	public ResponseEntity<?> ReadDataFromJson(String file)
	
	{
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

}
