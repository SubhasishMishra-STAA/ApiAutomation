package package2;

import static package1.Test1.*;

import java.util.concurrent.TimeUnit;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Test2 {

	public static void main(String[] args) {
		
		Response getResponse=given()
		.param("page", "2")
		.auth().none()
		.when().get("https://reqres.in/api/users");
//		.then()
//		.statusCode(200).body("page", equalTo(2));

//		getResponse.getTime();
//		getResponse.getTimeIn(TimeUnit.SECONDS);
//		getResponse.time();
//		getResponse.timeIn(TimeUnit.SECONDS);
		
		System.out.println("1-Time is " +getResponse.getTime());
		System.out.println("2- Time is " +getResponse.getTimeIn(TimeUnit.SECONDS));
		System.out.println("3-Time is " +getResponse.time());
		System.out.println("4- Time is " +getResponse.timeIn(TimeUnit.SECONDS));

	}

}
