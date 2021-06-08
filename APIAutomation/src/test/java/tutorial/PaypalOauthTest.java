package tutorial;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class PaypalOauthTest {
	static String access_token;
	static String clientId="AQE3fYC0PjbO4umyO6rfYA6G_wla6foTNeOcUF9eWrpos6RZ5e_SPhE5gIADUsUi_qCDyirv6yPG7vIh";
	static String secret="EJv65UlrDKIU1hYfms4d41p1idI_NQKME4S9QZMH-21vuWbRXHo4Nt_d4xkyAdYGMsLq3bViUHVJtGjO";
	@Test(priority=1)
	public void getAccessToken() {
		
		Response response=given()
		.auth().preemptive()
		.basic(clientId, secret)
		.param("grant_type", "client_credentials")
		.log().all()
		.post("https://api-m.sandbox.paypal.com/v1/oauth2/token");
		
		response.prettyPrint();
		System.out.println("Status code is " +response.statusCode());
		access_token=response.getBody().path("access_token");
		System.out.println("Access token is " +access_token);

	}
	
	@Test(priority=2,dependsOnMethods="getAccessToken")
	public void createPaypalOrder() {
		
		String payload="{\n" + 
				"  \"intent\": \"CAPTURE\",\n" + 
				"  \"purchase_units\": [\n" + 
				"    {\n" + 
				"      \"amount\": {\n" + 
				"        \"currency_code\": \"USD\",\n" + 
				"        \"value\": \"100.00\"\n" + 
				"      }\n" + 
				"    }\n" + 
				"  ]\n" + 
				"}";
		Response response=
				given().contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				.body(payload)
				.log().all()
				.post("https://api-m.sandbox.paypal.com/v2/checkout/orders");
			
		response.prettyPrint();
		System.out.println("Status code is " +response.statusCode());
		
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertEquals(response.getBody().path("status"), "CREATED");
	}
	
	

}
