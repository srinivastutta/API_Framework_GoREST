package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import io.restassured.response.Response;

public class UpdateUserTest {
	
	
	
	String baseURL = "https://gorest.co.in";
	String basePath = "/public-api/users/122";
	String token = "BrLSg_8Ney2mmqS0aQzUavzGGBoDdiz5fAHU";
	
	public static Map<String, String> authTokenMap;
	
		
	@Test
	public void updateUserAPITest() {
		
		authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		User user = new User("Srinivas", "tutta", "male", "01-01-1990","sri@gmail.com", "1313131313", "https://srinivas.com", "test adderss", "active");
	
		Response response = RestClient.doPut("JSON", baseURL, basePath, authTokenMap, null, true, user);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		System.out.println("=======================================");
		
	}
	

}
