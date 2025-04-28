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
import utilities.EndPoints;
import utilities.ReadingPropertiesFile;
import utilities.Utils;

public class testCase2 extends BasePage {
	
	public static String Token;
	public static String id;
	public static Logger logger = LogManager.getLogger(testCase2.class);
	
	
	@Test(priority=1)
	public void create_token_from_Auth() throws Exception{
		
		logger.info("Creating authentication token");
		String body_data = data_pojo_class_login.login_data();
		
		Response server_resp =restClient.post_Authorization(EndPoints.getAuth(), body_data)
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
		
		logger.info("Creating a new booking");
		String body_string = data_pojo_class_create.payload_data_file();

		Response server_resp = restClient.post_create_data(EndPoints.getCreateBooking(), Token, body_string)
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
	public void booking_partial_update() throws Exception{
		
		logger.info("Performing partial update of booking");
		
		String body_string = Utils.generateStringFromResource("/testData/partial_update_data.json");
		Response server_resp = restClient.partial_update(EndPoints.getPartialUpdate() + id, Token, body_string)
										 .then()
										 .log()
										 .all()
										 .assertThat()
										 .statusCode(200)
										 .extract()
										 .response();
		
		server_resp.prettyPeek();
		
		pojo_booking_create_data updateResponse = objectMapper.readValue(server_resp.asString(), pojo_booking_create_data.class);
	    String Updated_fname = updateResponse.getFirstname();
		
		logger.info("******* Verifying that FName  is same as the updated or not *******");
		Assert.assertEquals(Updated_fname, ReadingPropertiesFile.getProperty("updated_fname"));
		

	}
	

	@Test(priority=4)
	public void booking_delete_data() throws Exception{
		
		logger.info("Deleting booking");
		
		Response server_resp = restClient
							  .delete(EndPoints.getDelete() + id, Token)
							  .then()
							  .log()
							  .all()
							  .assertThat()
							  .statusCode(201)
							  .extract()
							  .response();
		server_resp.prettyPeek();
		
		logger.info("******* The data is deleted after processing *******");
		
	}
	

}
