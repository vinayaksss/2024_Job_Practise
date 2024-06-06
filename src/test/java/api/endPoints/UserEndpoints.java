package api.endPoints;


import org.apache.commons.compress.archivers.zip.ResourceAlignmentExtraField;
import org.testng.annotations.Test;

import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.ResourceBundle;

public class UserEndpoints {
	static String token="9ad219c75f5a1f2388d152e1f7a132b98b028b8012f5adc6e679d3401b290aaf";
	//mehod created for getting URLS from prop file
	static ResourceBundle getURl(){
		
		ResourceBundle res =ResourceBundle.getBundle("routes");//load properties file
		return res;
	}
	
	public static Response createUser(User payload) {
		String postURl=getURl().getString("postURL");
		System.out.println("Post URl fected Using ResourceBundle->"+postURl);
		
		Response response=given()
		.headers("Authorization", "Bearer "+token)		
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.postURL);
		
		return response;
		
		
	}
	
	
	public static Response getUser(String ID) {
		
		Response response=given()
				.headers("Authorization", "Bearer "+token)	
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userid", ID)			
				.when()
				.get(Routes.getURL);
		return response;
		
		
	}
	
	public static Response updateUser(String id,User payload) {
		Response response=given()
				.headers("Authorization", "Bearer "+token)	
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userid", id)	
				.body(payload)
				.when()
				.put(Routes.putURL);
		return response;
		
	}
	
	public static Response deleteUser(String ID) {
		Response response=given()
				.headers("Authorization", "Bearer "+token)	
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userid", ID)			
				.when()
				.delete(Routes.deleteURL);
				return response;
		
			
	}

}
