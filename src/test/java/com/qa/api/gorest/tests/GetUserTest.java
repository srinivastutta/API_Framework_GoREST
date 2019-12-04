package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {
	
	String baseURL = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW";
	
	public static Map<String, String> authTokenMap;
	
	@Test(priority = 1)
	public void getAllUserListAPITest() {
		
		authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		Response response = RestClient.doGet("JSON", baseURL, basePath, authTokenMap, null, true);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	@Test(priority = 2)
		public void getUser_withQueryParamsTest() {
		
		Map<String, String > params = new HashMap<String, String> ();
		params.put("first_name", "Rose");
		params.put("status", "active");
		params.put("email", "jermey.hahn@example.net");
		
		Response response = RestClient.doGet("JSON", baseURL, basePath, authTokenMap, params, true);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
}
