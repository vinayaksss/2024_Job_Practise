package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndpoints;
import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;

public class UserTests {

	Faker fake;
	User payLoad;
	static int id;
	Response res;
	
	public Logger log;
	

	@BeforeClass
	public void setUp() {
		fake = new Faker();
		payLoad = new User();
		payLoad.setName(fake.name().firstName());
		payLoad.setGender("Male");
		payLoad.setEmail(fake.internet().emailAddress());
		payLoad.setStatus("Active");
		log=LogManager.getLogger(this.getClass());
		
		log.info("debugging.....");
		

	}

	
	@Test(priority = 1)
	
	void testCreateUser(){
		log.info("Creating User");
		res=UserEndpoints.createUser(payLoad);
		res.then().log().body();
		System.out.println("Create User "+res.statusCode());
		Assert.assertEquals(res.statusCode(), 201);
		
		
	}
	
	@Test(priority = 2)
	
	void getUserByName() {
		
		res=UserEndpoints.getUser(res.jsonPath().getString("id"));
		res.then().log().body();
		System.out.println("Get User "+res.statusCode());
		Assert.assertEquals(res.statusCode(), 200);
		
	}
	
@Test(priority = 3)
	
	void updateUserByName() {
		
		res=UserEndpoints.updateUser(res.jsonPath().getString("id"),payLoad);
		res.then().log().body();
		System.out.println("Updated User "+res.statusCode());
		Assert.assertEquals(res.statusCode(), 200);
		
	}

@Test(priority = 4)

void deleteUserByName() {
	
	res=UserEndpoints.deleteUser(res.jsonPath().getString("id"));
	res.then().log().body();
	System.out.println("Delete User "+res.statusCode());
	Assert.assertEquals(res.statusCode(), 204);
	
}
	
}
