package testCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import payloads.dynamic_auth_data;
import payloads.pojo_class_auth_data;
import payloads.pojo_booking_create_data;
import repeatables.BasePage;
import repeatables.data_pojo_class_create;
import repeatables.data_pojo_class_login;
import repeatables.data_pojo_class_update;
import utilities.EndPoints;
import utilities.ReadingPropertiesFile;



public class testCase1 extends BasePage {

	public static String Token;
	public static String id;
	public static Logger logger = LogManager.getLogger(testCase1.class);
	protected static ReadingPropertiesFile readingPropertiesFile = new ReadingPropertiesFile();
	
	@Test(priority=1)
	public void create_token_from_Auth() throws Exception{
		
		logger.info("******* create_token_from_Auth *******");
		// Taking the JSON passed in body in a dynamic way. 
		
//		String username = ReadingPropertiesFile.getProperty("username");
//		String password = ReadingPropertiesFile.getProperty("password");
//		String body_data = dynamic_auth_data.auth_payload(username, password);
		
		
		String body_data = data_pojo_class_login.login_data();
		
		Response server_resp =restClient.post_Authorization(EndPoints.auth, body_data)
										.then()
										.assertThat()
										.statusCode(200)
										.extract()
										.response();
		
		server_resp.prettyPeek();
		
		pojo_class_auth_data authResponse = server_resp.as(pojo_class_auth_data.class);
		Token = authResponse.getToken();
		
		logger.info("******* Verifying that tokken is Not Null *******");
		Assert.assertNotNull(Token);
		
	}
	
	
	@Test(priority=2)
	public void booking_create() throws Exception{
		
		logger.info("******* Create a BookingPayload instance with data *******");
		// Taking data for body using a POJO class
		
	    String body_string = data_pojo_class_create.payload_data_file();

	    Response server_resp = restClient.post_create_data(EndPoints.createBooking, Token, body_string)
	            .then()
	            .assertThat()
	            .statusCode(200)
	            .extract()
	            .response();

	    server_resp.prettyPeek();
	    
	    pojo_class_auth_data bookingResponse = objectMapper.readValue(server_resp.asString(), pojo_class_auth_data.class);
	    id = String.valueOf(bookingResponse.getBookingid());
	    String LName = bookingResponse.getBooking().getLastname();

	    logger.info("******* Verifying that Last Name is the same as input or not *******");
	    Assert.assertEquals(LName, ReadingPropertiesFile.getProperty("lName"));
		
	}
	
	
	@Test(priority=3)
	public void booking_update() throws Exception {
		
		logger.info("******* Updating the Created Booking Data  *******");
		
		
		String body_data= data_pojo_class_update.payload_data_file();
		Response server_resp = restClient.update(EndPoints.update_booking+id,body_data,Token)
								.then()
								.statusCode(200)
								.extract()
								.response();
		
		server_resp.prettyPeek();
		
		pojo_booking_create_data updateResponse = objectMapper.readValue(server_resp.asString(), pojo_booking_create_data.class);
	    String updatedLastName = updateResponse.getLastname();
		
		logger.info("******* Verifying that Last Name is same as updated data or not  *******");
		Assert.assertEquals(updatedLastName,ReadingPropertiesFile.getProperty("updated_lname"));
		
		
			
	}
	
	
	@Test(priority=4)
	public void get_booking_by_id() throws Exception{
		
		logger.info("******* Featching the Booking Details by id  *******");
		
		Response server_resp = restClient.get_data(EndPoints.getById+id, Token)
								.then()
								.log()
								.all()
								.assertThat()
								.statusCode(200)
								.extract()
								.response();
		
		server_resp.prettyPeek();
		
		pojo_booking_create_data bookingDetails = objectMapper.readValue(server_resp.asString(), pojo_booking_create_data.class);
	    int total = bookingDetails.getTotalprice();
	    
	    logger.info("******* Verifying that totalprice is same as input data or not  *******");
	    Assert.assertEquals(String.valueOf(total),ReadingPropertiesFile.getProperty("totalprice"));
		
	}
	
}
