package tutorial;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostAPI {

public static void main(String[] args) {
			
String payload="{\"name\": \"morpheus\",\"job\": \"leader\"}";

HashMap<String,String> map =new HashMap<String,String>();
map.put("name", "morpheus");
map.put("job", "leader");


			
//		given()
//		.auth().none()
//		.header("Content-Type","application/json")
//		.contentType(ContentType.JSON)
//		.when()
//		.body(payload)
//		.post("https://reqres.in/api/users")
//		.then()
//		.statusCode(201)
//		.body("name", equalTo("morpheus"),
//		"job", equalTo("leader"));

Response postResponse=given()
.auth().none()
.header("Content-Type","application/json")
.contentType(ContentType.JSON)
.when()
.body(new File("./Payload.json")).log().all()
.post("https://reqres.in/api/users");

System.out.println("Post Response is "+postResponse.asString());
System.out.println("Post Response status code is "+postResponse.statusCode());

}

}
