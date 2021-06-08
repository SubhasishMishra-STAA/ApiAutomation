package tutorial;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAPIReadCookies {

	public static void main(String[] args) {
		
		System.out.println("1st API TC");
//		given().
//		param("page", "2")
//		.auth().none()
//		.header("Content-Type","application/json")
//		.contentType(ContentType.JSON)
//		.when()
//		.get("https://reqres.in/api/users")
//		.then()
//		.statusCode(200)
//		.body("page", equalTo(2),
//		"per_page", equalTo(6));
		
		Response getResponse=given().
		param("page", "2")
		.auth().none()
		.header("Content-Type","application/json")
		.contentType(ContentType.JSON)
		.when()
		.get("https://reqres.in/api/users");

		
		// get All Cookies
		System.out.println("Response Cookies are " +getResponse.getCookies());	
		// get Cookies By cookie name
		System.out.println( "__cfduid is " +getResponse.getCookie("__cfduid"));
		
		//getResponse.prettyPrint();
		System.out.println("Response is " +getResponse.asString());
		
		System.out.println("Status code is " +getResponse.statusCode());
		
		System.out.println("Response Time is " +getResponse.getTime());
		//getResponse.body("page",equalTo(2));
		
		System.out.println("Executed successfully");
		
	}
}
