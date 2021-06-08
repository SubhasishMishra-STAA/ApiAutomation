package package2;


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
          
          postResponse.getHeaders();
          System.out.println("All the headers are " +postResponse.getHeaders());
          System.out.println("Headers-Content-Type " +postResponse.header("Content-Type"));
          System.out.println("Headers-Date " +postResponse.header("Date"));
          
          
          System.out.println("All the cookies are " +postResponse.getCookies());

          
          System.out.println("All the Content Type are " +postResponse.getContentType());


          
//          Assert.assertEquals(postResponse.getBody().path("name"), "morpheus");
//          Assert.assertEquals(postResponse.getBody().path("job"), "leader");
//          Assert.assertEquals(postResponse.getBody().path("skills[0]"), "Java");
//          Assert.assertEquals(postResponse.getBody().path("skills[1]"), "C");
//          Assert.assertEquals(postResponse.getBody().path("details.companyName"), "XYZ");
//          Assert.assertEquals(postResponse.getBody().path("details.emailId"), "morpheus@xyz.com");
//
//          Assert.assertEquals(postResponse.jsonPath().get("name"), "morpheus");
//          Assert.assertEquals(postResponse.jsonPath().get("job"), "leader");
          
          
//				.then().log().all()
//				.body("name", equalTo("morpheus"),
//					 "job", equalTo("leader"),
//					 "skills[0]", equalTo("Java"),
//					 "skills[1]", equalTo("C"),
//					 "details.companyName", equalTo("XYZ"),
//					 "details.emailId", equalTo("morpheus@xyz.com"));
				
	}

}
