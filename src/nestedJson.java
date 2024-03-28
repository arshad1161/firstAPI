import org.testng.Assert;

import files.payload;
import files.resuableMethods;
import io.restassured.path.json.JsonPath;

public class nestedJson {

	public static void main(String[] args) {
		
	JsonPath js = resuableMethods.rawToJson(payload.coursePrice());
		
		// 1. Print No of courses returned by API
	
	int count= js.getInt("courses.size()");
	System.out.println(count);
	
		// 2.Print Purchase Amount
	int purchaseAmount = js.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseAmount);

		// 3. Print Title of the first course
	String firstTitle = js.get("courses[0].title");
	System.out.println(firstTitle);

		// 4. Print All course titles and their respective Prices
	for(int i=0;i<count;i++)
	{
		String allTitles = js.get("courses["+i+"].title");
		//System.out.println(allTitles);
		
		int allPrices = js.get("courses["+i+"].price");
		System.out.println(allTitles +" = "+ allPrices);
	}

		// 5. Print no of copies sold by RPA Course
	for(int i=0;i<count;i++)
	{
		String courseTiltles = js.get("courses["+i+"].title");
		if(courseTiltles.equalsIgnoreCase("RPA"))
		{
			int rpaCopies = js.get("courses["+i+"].copies");
			System.out.println("Print no.of copies: "+rpaCopies );
			break; //if for loop find the condition it will stop
		}
		else if(courseTiltles.equalsIgnoreCase("RPA"))
		{
			int rpaCopies = js.get("courses["+i+"].copies");
			System.out.println("Print no.of copies: "+rpaCopies );
			break; //if for loop find the condition it will stop
		}
		
	}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount
	int sum=0;
	for(int i=0;i<count;i++)
	{
		int  price = js.get("courses["+i+"].price");
		int copies = js.get("courses["+i+"].copies");
		int total = price*copies;
		System.out.println(total);
		sum= sum+total;
	}
	System.out.println(sum);
	int purchaseAmount1 = js.get("dashboard.purchaseAmount");
	Assert.assertEquals(sum, purchaseAmount1);
	}
}
