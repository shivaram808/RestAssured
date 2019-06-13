package restprograms;

import javax.annotation.meta.When;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*; 
//We require this static import inorder to get BDD format "given when then"


public class Getcall {
	
	//@Test
	public void verify_getcall()
	{
		Response response = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22");
		
		System.out.println("responce code is : "+response .getStatusCode());
		
		
		String responce_output = response.asString();
		
		System.out.println("Responce output is : "+responce_output);
		
		String name = response.jsonPath().getString("name");
		System.out.println("Res : "+name);
		
		String wind = response.jsonPath().getString("wind");
		
	
		System.out.println("Weather results are  : "+wind);
		
		}
	
	// Verify status code in BDD style 
	@Test
	public void get_data_BDDstyle()
	{
			given().get("http://services.groupkt.com/country/search?text=lands").
			then().statusCode(200).
			log().all();
	}
	
	
	// Verify if given data is available in obtained response
	//@Test
	public void get_verifyData()
	{
	
		given().get("http://services.groupkt.com/country/search?text=lands").
		then().body("RestResponse.result.alpha2_code", hasItems("FK", "AX"));

	}
	
	// Fetching required data from response, Get wind speed to a particular city
	//@Test
	public void fetch_desiredData()
	{
	
		Response response = given().get("http://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		String Speed = jsonPathEvaluator.get("wind.speed");
		
		System.out.println("wind speed in this region is :  " +Speed);
		

	}
	
// Fetching list of data from result	
	@Test
	public void fetch_ListData()
	{
	
		Response response = given().get("http://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		 Object object = jsonPathEvaluator.get("main");
		
		System.out.println("wind speed in this region is :  " +object.toString());
		

	}
	
}
