package tutorial;


import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostApiUsingPojo {

	public static void main(String[] args) {
		
		EmployeePojo ep = 
				new EmployeePojo("morpheus", "leader",
						new String[] {"Java","C"}, "XYZ", "morpheus@xyz.com");
		
		Response postResponse=given()
				.auth().none()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when()
				.body(ep).log().all()
				.post("https://reqres.in/api/users");
		
		postResponse.prettyPrint();
		
//		String name= postResponse.getBody().path("name");
//		System.out.println("name is " +name);
//		System.out.println("job is " +postResponse.getBody().path("job"));
//		System.out.println("skill 1 is " +postResponse.getBody().path("skills[0]"));
//		System.out.println("skill 2 is " +postResponse.getBody().path("skills[1]"));
//		System.out.println("companyName is " +postResponse.getBody().path("details.companyName"));
//		System.out.println("emailId is " +postResponse.getBody().path("details.emailId"));
//		
		
		Assert.assertEquals(postResponse.getBody().path("name"), "morpheus");
		Assert.assertEquals(postResponse.getBody().path("job"), "leader");
		Assert.assertEquals(postResponse.getBody().path("skills[0]"), "Java");
		Assert.assertEquals(postResponse.getBody().path("skills[1]"), "C");
		Assert.assertEquals(postResponse.getBody().path("details.companyName"), "XYZ");
		Assert.assertEquals(postResponse.getBody().path("details.emailId"), "morpheus@xyz.com");
//		
//		
//		String name= postResponse.jsonPath().get("name");
//		System.out.println("name is " +name);
//		System.out.println("job is " +postResponse.jsonPath().get("job"));
//		System.out.println("skill 1 is " +postResponse.jsonPath().get("skills[0]"));
//		System.out.println("skill 2 is " +postResponse.jsonPath().get("skills[1]"));
//		System.out.println("companyName is " +postResponse.jsonPath().get("details.companyName"));
//		System.out.println("emailId is " +postResponse.jsonPath().get("details.emailId"));
		
		
		
		
//				System.out.println("Post Response is "+postResponse.asString());
//				System.out.println("Post Response status code is "+postResponse.statusCode());

	}

}
