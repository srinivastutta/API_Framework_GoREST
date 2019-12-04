package com.qa.api.imgur.tests;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {
	
	Map<Object, Object> tokenMap;
//	String accessToken;
	String accountUsername;
//	String refreshToken;
	
	public static Map<String, String> authTokenMap;
	
	@BeforeMethod
	public void Setup() {
		
 		tokenMap = Token.getGenerateToken();
//		accessToken= tokenMap.get("access_token").toString();
		accountUsername = tokenMap.get("account_username").toString();
//		refreshToken = tokenMap.get("refresh_token").toString();
		
		authTokenMap=Token.getAuthToken();
		
		
	}
	
	
	@Test
	public void accountBlockstatusTest() {
	
		
	Response response =	RestClient.doGet(null, "https://api.imgur.com", "/account/v1/"+accountUsername+"/block", 
	authTokenMap, null, true);
		
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
		
		
	}
	@Test
	public void AccountBaseTest() {
	Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/"+accountUsername, 
	authTokenMap, null, true);
	
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
		
	}
	@Test
	public void AccountImageTest() {
	Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images", 
	authTokenMap, null, true);
	
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
		
	}
	@Test
	public void AccountSettingsTest() {
	Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/settings", 
	authTokenMap, null, true);
	
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void getAccountImageTest() {
	Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images", 
	authTokenMap, null, true);
	
	System.out.println(response.statusCode());
	System.out.println(response.prettyPrint());
		
	}
}
