package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Epic("GoREST API Delete User feature implementation...")
@Feature("Deleting User API feature...")
public class DeleteUserTest {
	
	
	String baseURL = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "BrLSg_8Ney2mmqS0aQzUavzGGBoDdiz5fAHU";
	
	public static Map<String, String> authTokenMap;
	public static JsonPath jp;
	public static String userId;
	public static String firstname;
	//Creating User 

	@Description("Creating user")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void createUserAPITest() {
		
		authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		User user = new User("Srini", "tutta", "male", "01-01-1990","srini6@gmail.com", 
		"1313131313", "https://srinivas.com", "test adderss", "active");
		
		Response response = RestClient.doPost("JSON", baseURL, basePath, authTokenMap, null, true, user);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		jp = response.jsonPath();
		
		System.out.println(jp.getString("result.id"));
		
		userId= jp.getString("result.id");
		firstname =jp.getString("result.first_name");
		System.out.println(firstname);
		
	}
	@Description("Delete user")
	@Severity(SeverityLevel.NORMAL)
		//Delete Same User
	@Test(priority = 2)
	public void updateUserAPITest() {
		
		authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		
		
		Response response = RestClient.doDelete("JSON", baseURL, basePath+"/"+userId, authTokenMap, null, true, null);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		System.out.println("=======================================");
		
	}
	//Verify User is deleted or not through GET Call
	@Description("Verifying that user is deleted or not")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void VeriyUserAPITest() {
		
		authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		
		Response response = RestClient.doGet("JSON", baseURL, basePath+"?first_name="+firstname, authTokenMap, null, true);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		jp = response.jsonPath();
		
		System.out.println(jp.getString("_meta.totalCount"));
		
		String totalCount= jp.getString("_meta.totalCount");
		
		//Assert.assertEquals(totalCount, "0");
		
	}

}
