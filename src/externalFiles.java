import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class externalFiles {

	public static void main(String[] args) throws IOException
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response =	given().log().all(). queryParam("key", "qaclick123")
			.body(new String( Files.readAllBytes(Paths.get("C:\\Users\\arshe\\OneDrive\\Desktop\\addbook.json"))))  
			.when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
			//convert response into string
			.extract().response().asString();
		System.out.println(response);
		
	//	JsonPath js=new JsonPath(response); //for parsing Json
		//String placeID=js.getString("place_id");
	// 	System.out.println("Place ID is "+placeID);
		
	}
}
