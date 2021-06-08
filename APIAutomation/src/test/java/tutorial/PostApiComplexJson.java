package tutorial;


import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostApiComplexJson {

	public static void main(String[] args) {
	
		HashMap<String,Object> map =new HashMap<String,Object>();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
		ArrayList<String> sk= new ArrayList<String>();
		sk.add("Java");
		sk.add("c");
		
		map.put("skills", sk);
		
		HashMap<String,Object> det =new HashMap<String,Object>();
		det.put("companyName", "XYZ");
		det.put("emailId", "morpheus@xyz.com");
		
		map.put("details", det);
		
		Response postResponse=given()
				.auth().none()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when()
				.body(map).log().all()
				.post("https://reqres.in/api/users");

				System.out.println("Post Response is "+postResponse.asString());
				System.out.println("Post Response status code is "+postResponse.statusCode());

				}
	
	

}
