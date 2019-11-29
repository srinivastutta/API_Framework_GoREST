package com.qa.api.gorest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURL = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "sz54-2RPwMeyNOsRUZHMkLUPBXPChHbk-sZW";
	
	@DataProvider
	public Object [][] getUserData() {
		Object [][] userData = ExcelUtil.getTestData("testdata");
		return userData;
	}
	
	
	@Test(dataProvider ="getUserData")
	public void createUserAPITest(String firstname, String lastname, String gender, String dob, String email,
			String phonenumber, String website, String address, String status) {
		
		User user = new User(firstname, lastname, gender, dob,email, phonenumber, website, address, status);
	
		Response response = RestClient.doPost("JSON", baseURL, basePath, token, null, true, user);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		System.out.println("=======================================");
		
	}
	
	
}
