package utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientWrapper {

	public String resource;
	public String baseUrl;
	private RequestSpecification request;
	private Response restResponse;
	
	public RestClientWrapper(String baseUrl,RequestSpecification request) {
		
		this.request = request;
		this.request.baseUri(baseUrl);	
		
		}
	
	public Response post_Authorization(String end_point, String bodyString_data)throws Exception{
		  restResponse = request.header("Accept","application/json")
							  .header("Content-Type","application/json")
							  .body(bodyString_data)
							  .when()
							  .post(end_point);
		return restResponse;
		}
	
	
	public Response post_create_data(String endpoint,String token,String bodyString_data) {    
	       
		restResponse = request.queryParam("token",token)
	                			 .header("Accept","application/json")
	                			 .header("Content-Type","application/json")
	                			 .body(bodyString_data)
	                			 .when()
	                			 .post(endpoint);         
	        return restResponse;  
	        
	    }
		

	public Response update(String resource, String bodyString_data_to_change,String Token) {

		restResponse = request.header("Content-Type", "application/json")
                			  .header("Accept", "application/json")
                			  .header("Cookie", "token="+Token)
                			  .body(bodyString_data_to_change)
                			  .when()
                			  .put(resource);
			return restResponse;
			
		}
 
	public Response get_data(String endpoint,String Token)throws Exception {
	
		restResponse = request.queryParam("token",Token)
						 	  .header("Accept","application/json")
						 	  .when()
						 	  .get(endpoint);
						
		return restResponse;
	
		}

	public Response partial_update(String end_point,String Token, String bodyString_data_to_change) {
	
		restResponse = request.header("Content-Type", "application/json")
							  .header("Accept", "application/json")
							  .header("Cookie", "token="+Token)
							  .body(bodyString_data_to_change)
							  .when()
							  .patch(end_point);

		return restResponse;
	
		}

	public Response delete(String end_point,String Token) {
			
			restResponse = request.header("Content-Type", "application/json")
								  .header("Accept", "application/json")
								  .header("Cookie", "token="+Token)
								  .when()
								  .delete(end_point);
			
			return restResponse;
		}
	
}
