package com.qa.api.imgur.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class PostImgurAPITest {
//"multpart" is content type for image uploads
	
	@Test
	public void uploadImagePostAPITest() {
		
		Map<String, String> clientIdMap = Token.getClientId();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "test title API");
		formMap.put("description", "test description API");
		
		
		Response response =RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null , true, formMap);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void UpdateImageInfoPostAPITest() {
		
		Map<String, String> clientIdMap=Token.getClientId();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "Srinivas");
		formMap.put("description", "Srinivas Desc");
		
		
		Response response =RestClient.doPost("multipart", "https://api.imgur.com", "/3/image/7tE53V6",
				clientIdMap, null, true, formMap);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
}
