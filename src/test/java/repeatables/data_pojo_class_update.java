package repeatables;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import payloads.pojo_booking_create_data;
import payloads.pojo_booking_dates_data;
import utilities.ReadingPropertiesFile;

public class data_pojo_class_update {


public static  String payload_data_file() throws JsonProcessingException {
		
	 	// Create a BookingPayload instance with data
	
		pojo_booking_create_data payload = new pojo_booking_create_data();
	    pojo_booking_dates_data bookingDates = new pojo_booking_dates_data();
	    
		payload.setFirstname(ReadingPropertiesFile.getProperty("fName"));
	    payload.setLastname(ReadingPropertiesFile.getProperty("updated_lname"));
	    payload.setTotalprice(Integer.parseInt(ReadingPropertiesFile.getProperty("totalprice")));
	    payload.setDepositpaid(true);
	    bookingDates.setCheckin(ReadingPropertiesFile.getProperty("checkin"));
	    bookingDates.setCheckout(ReadingPropertiesFile.getProperty("checkout"));
	    payload.setBookingdates(bookingDates);
	    payload.setAdditionalneeds(ReadingPropertiesFile.getProperty("needs"));
    
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    String body_string = objectMapper.writeValueAsString(payload);
	    return body_string;
	}
}
