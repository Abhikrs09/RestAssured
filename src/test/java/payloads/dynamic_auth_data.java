package payloads;

public class dynamic_auth_data {

	public static String auth_payload(String Username, String Password) {
		String json = "{\r\n"
				+ "  \"username\": \""+Username+"\",\r\n"
				+ "  \"password\": \""+Password+"\"\r\n"
				+ "}";
		return json;
	}
    
}
