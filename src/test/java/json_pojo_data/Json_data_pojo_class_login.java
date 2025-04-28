package json_pojo_data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import payloads.pojo_login_data;

import java.io.File;
import java.io.IOException;

public class Json_data_pojo_class_login {

    public static String login_data() throws IOException {

        // Define the path to your login.json file
//        File jsonFile = new File("src/test/resources/testData/auth_data.json");
        File jsonFile =  new File(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\auth_data.json");
        // Create ObjectMapper instance to parse JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON file to JsonNode
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        // Extract username and password from the JSON
        String username = rootNode.path("username").asText();
        String password = rootNode.path("password").asText();

        // Create POJO and set the values
        pojo_login_data payload = new pojo_login_data();
        payload.setUsername(username);
        payload.setPassword(password);

        // Convert POJO to JSON string
        return objectMapper.writeValueAsString(payload);
    }


}
