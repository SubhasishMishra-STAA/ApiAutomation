package complexjson;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import complexjson.Courses.ApiAutomation;
import complexjson.Courses.MobileAutomation;
import complexjson.Courses.WebAutomation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ComplexJson {

	public static void main(String[] args) throws IOException {
		
		List<WebAutomation> webs = new ArrayList<WebAutomation>();
		Courses.WebAutomation sel = new Courses.WebAutomation();
		sel.setCourseTitle("Selenium");
		sel.setPrice(4000);
		
		Courses.WebAutomation pro = new Courses.WebAutomation();
		pro.setCourseTitle("Protractor");
		pro.setPrice(5000);
		
		webs.add(sel);
		webs.add(pro);
		
		List<ApiAutomation> apis = new ArrayList<ApiAutomation>();
		Courses.ApiAutomation rest = new Courses.ApiAutomation();
		rest.setCourseTitle("RestAssure");
		rest.setPrice(5000);
		
		Courses.ApiAutomation soap = new Courses.ApiAutomation();
		soap.setCourseTitle("SOAP API Automation");
		soap.setPrice(3000);
		
		apis.add(rest);
		apis.add(soap);
		
		List<MobileAutomation> mob = new ArrayList<MobileAutomation>();
		Courses.MobileAutomation app = new Courses.MobileAutomation();
		app.setCourseTitle("Appium");
		app.setPrice(5000);
		
		mob.add(app);
		
		
		Courses courses= new Courses();
		courses.setWebAutomation(webs);
		courses.setApiAutomation(apis);
		courses.setMobileAutomation(mob);
			
		ServiceDetailsPojo sp = new ServiceDetailsPojo();
		sp.setInstructor("Subhasish");
		sp.setUrl("softwaretestingandautomation.com");
		sp.setServices("Software Testing");
		sp.setExpertise("Testing");
		sp.setCourses(courses);
		sp.setLinkedIn("linkedinId");	
		
		ObjectMapper objectMapper= new ObjectMapper();
		String convertedJson=objectMapper.writeValueAsString(sp);
		System.out.println("convertedJson is " +convertedJson);
		
//		String userDir=System.getProperty("user.dir");
//		File outputJsonFile= 
//				new File(userDir+"/src/test/resources/Payload.json");
//		
//		objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputJsonFile, sp);
				
		Response postResponse=given()
				.auth().none()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when()
				.body(convertedJson).log().all()
				.post("https://reqres.in/api/users");
		
		String response=postResponse.asString();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		ServiceDetailsPojo sdp = objectMapper.readValue(response, ServiceDetailsPojo.class);
		String instructor= sdp.getInstructor();
		String mobileautoCourceTitle=sdp.getCourses().getMobileAutomation().get(0).getCourseTitle();

		System.out.println("instructor name is" +instructor);
		System.out.println("mobile automation Cource Title  is" +mobileautoCourceTitle);
		
		}

}
