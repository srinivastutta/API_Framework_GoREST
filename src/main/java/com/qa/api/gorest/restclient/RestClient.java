package com.qa.api.gorest.restclient;

import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all http methods which will call the apis and having generic methods for getting
 * the response and fetch the values from response
 * 
 * @author Srinivas
 *
 */

public class RestClient {

	//HTTP Methods: GET POST PUT DELETE

	/**
	 * This method is used call GET APIs
	 * @param contentType
	 * @param baseURL
	 * @param basePath
	 * @param token
	 * @param paramMaps
	 * @param Log
	 * @return This method is returning response from the GET call
	 */
	
	
	public static Response doGet(String contentType, String baseURL, String basePath, 
			String token, Map<String, String> paramMaps, boolean Log) {
		
		if (setBaseURL(baseURL)) {
			RequestSpecification request = createRequest(contentType, token, paramMaps, Log);
			return getResponse("GET", request, basePath);
		}
		return null;
		}
	/**
	 * This method is used call POST APIs
	 * @param contentType
	 * @param baseURL
	 * @param basePath
	 * @param token
	 * @param paramMaps
	 * @param Log
	 * @param obj
	 * @return This method is returning response from the POST call
	 */
	public static Response doPost(String contentType, String baseURL, String basePath, 
			String token, Map<String, String> paramMaps, boolean Log, Object obj) {
		
		if (setBaseURL(baseURL)) {
			RequestSpecification request = createRequest(contentType, token, paramMaps, Log);
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;
		}
	
	public static void addRequestPayload(RequestSpecification request, Object obj) {
		String  JsonPayload = TestUtil.getSerializedJason(obj);
		request.body(JsonPayload);
	
	}
	
	private static boolean setBaseURL(String baseURL) {
		if (baseURL==null || baseURL.isEmpty()) {
			System.out.println("Please provide the Base URL....");
			return false;
		}
		try {
				RestAssured.baseURI = baseURL;
				return true;
		} catch (Exception e) {
				System.out.println("Some exception got occured while assigning the base URL with Rest Assured"
						+ "either it is null or blank/empty...");
				return false;
		}
	}
	
	private static RequestSpecification createRequest(String contentType, String token,  
			Map<String, String> paramMaps, boolean Log) {
		
		RequestSpecification request;
		if (Log) {
			request = RestAssured.given().log().all();
		}else {
			request = RestAssured.given();
		}
		
		if (token !=null) {
			request.header("Authorization", "Bearer "+token);
			
			}
		if (!(paramMaps==null)) {
			request.queryParams(paramMaps);
		}
		if (contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
			
		}
		else if (contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
			
		}
		else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
	}
		return request;
	
}
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
				
		
	}
	
	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "put":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			System.out.println("Please pass the correct HTTP Method...");
			break;
		}
		return response;
	}
}
