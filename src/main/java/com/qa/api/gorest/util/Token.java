package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;


public class Token {
	
	public static  Map<Object, Object> appTokenMap;
	
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	
	public static String clientId = "bca03b1a7adf811";
	
	public static Map<Object, Object> getGenerateToken() {
	
		Map<String, String > formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "359d10b075fe9cfb2f6b5328303f001448a3c848");
		formParams.put("client_id", "bca03b1a7adf811");
		formParams.put("client_secret", "fefb377ad7f613f767bce2aa18467e83566ed867");
		formParams.put("grant_type", "refresh_token");
		
		JsonPath tokenJason=
		given()
				.formParams(formParams)
						.when()
								.post("https://api.imgur.com/oauth2/token")
									.then()
										.extract()
											.jsonPath();
		System.out.println(tokenJason.getMap(""));
		
		appTokenMap=tokenJason.getMap("");
		return appTokenMap;
		
	}

	public static Map<String, String> getAuthToken() {
		String authToken =	appTokenMap.get("access_token").toString();
		System.out.println("Auth Token "+authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
		
	}
	
	public static Map<String, String> getClientId() {
		System.out.println("Client ID is "+clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		return tokenMap;
	
	}
	
}
