package repeatables;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import payloads.pojo_login_data;
import utilities.ReadingPropertiesFile;

public class data_pojo_class_login {
	
	
	public static String login_data() throws JsonProcessingException{
		
		pojo_login_data payload = new pojo_login_data();
		payload.setUsername(ReadingPropertiesFile.getProperty("username"));
		payload.setPassword(ReadingPropertiesFile.getProperty("password"));

		ObjectMapper  objmap = new ObjectMapper();
		String body = objmap.writeValueAsString(payload);
		return body;
	}

}
