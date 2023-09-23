package repeatables;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import payloads.pojo_booking_create_data;
//import payloads.pojo_booking_partial_update_data;
import utilities.ReadingPropertiesFile;

public class data_pojo_class_partial_update {

	public static String payload_data_file() throws JsonProcessingException {
		
		// Create a BookingPayload instance with data
		
		pojo_booking_create_data payload = new pojo_booking_create_data();
	    
		payload.setFirstname(ReadingPropertiesFile.getProperty("updated_fname"));
	    payload.setLastname(ReadingPropertiesFile.getProperty("lName"));
		
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    String body_string = objectMapper.writeValueAsString(payload);
	    return body_string;
	    
	}
	
}
