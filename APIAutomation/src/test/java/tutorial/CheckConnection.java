package tutorial;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CheckConnection {

	public static void main(String[] args) {
		System.out.println("1st API TC started");
		given().param("page", "2").auth().none().when()
		.get("https://reqres.in/api/users").then().statusCode(200)
		.body("page", equalTo(2), "per_page", equalTo(6));
		System.out.println("1st API TC ended");
	}
}
