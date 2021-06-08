package tutorial;


import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostApirunTime {

	public static void main(String[] args) {
		JSONObject jo = new JSONObject();
		jo.put("name", "morpheus");
		jo.put("job", "leader");
		
		JSONArray ja = new JSONArray();
		ja.put("Java");
		ja.put("C");
		
		jo.put("skills", ja);
		
		JSONObject de = new JSONObject();
		de.put("companyName", "XYZ");
		de.put("emailId", "morpheus@xyz.com");
		
		jo.put("details", de);
		
		Response postResponse=given()
				.auth().none()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when()
				.body(jo.toString()).log().all()
				.post("https://reqres.in/api/users");

				System.out.println("Post Response is "+postResponse.asString());
				System.out.println("Post Response status code is "+postResponse.statusCode());


	}

}
