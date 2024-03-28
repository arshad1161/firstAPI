



import files.payload;
import files.resuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class DynamicJson {
	
	@Test(dataProvider = "Bookdata")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().log().all(). header("Content-Type","application/json").body(payload.AddBook(isbn,aisle)).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
	JsonPath js=resuableMethods.rawToJson(response);
		
		String id=js.get("ID");
		System.out.println(id);
	}
	@DataProvider(name = "Bookdata")
	public Object[][] getData()
	{
		//array = collection of elements
		//multidimensional array = collection of array
		// [][] - This will refer as two dimensional array
		return new Object[][] {{"ararqqqq1q","1212341"},{"qeqqeeqqq","41156578"}};
		
	}
	


}
