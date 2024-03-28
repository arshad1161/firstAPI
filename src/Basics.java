import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
import files.resuableMethods;

public class Basics {

	public static void main(String[] args) {
		
// Validate ADD place API working as expected
		
		//given - all input details i.e parameters, headers, authorization, body
		//when - Submit the API i.e. HTTP method & resource
		//then - Validate the Response
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		//given().log().all().queryParam("key", "qaclick123")
		//.body(payload.AddPlace())
		//.when().post("/maps/api/place/add/json")
		//.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)");
		
		//TASK -- Add Place -> Update the place with New address -> Get place to validate if New Address is preset in the response
		//Add Place 
		RestAssured.baseURI = "https://rahulshettyacademy.com";
			String response =	given().log().all().queryParam("key", "qaclick123")
				.body(payload.AddPlace())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				//convert response into string
				.extract().response().asString();
			System.out.println(response);
			
			JsonPath js=new JsonPath(response); //for parsing Json
			String placeID=js.getString("place_id");
			System.out.println("Place ID is "+placeID);
			
			//Update the place with New address
			String newAddress= "Jawahar Street";
			given().queryParam("key", "qaclick123")
			.body("{\r\n"
					+ "\"place_id\":\""+placeID+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("/maps/api/place/update/json")
			.then().assertThat().log().all(). statusCode(200).body("msg", equalTo("Address successfully updated"));
			
			//Get place to validate if New Address is preset in the response
			String getPlaceResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
			.when().get("/maps/api/place/get/json")
			.then().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath js1=resuableMethods.rawToJson(getPlaceResponse);
			String actaulResult= js1.getString("address");
			System.out.println(actaulResult);
			Assert.assertEquals(actaulResult, newAddress);
			
	}

}
