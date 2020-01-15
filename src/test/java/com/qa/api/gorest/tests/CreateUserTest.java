package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("GoREST API GET User feature implementation...")
@Feature("Get User API feature...")

public class CreateUserTest {
	
	String baseURL = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "BrLSg_8Ney2mmqS0aQzUavzGGBoDdiz5fAHU";
	
	public static Map<String, String> authTokenMap;
	
	
	@DataProvider
	public Object [][] getUserData() {
		Object [][] userData = ExcelUtil.getTestData("testdata");
		return userData;
	}
	
	@Description("Create multiple user by using data provider")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider ="getUserData")
	public void createUserAPITest(String firstname, String lastname, String gender, String dob, String email,
			String phonenumber, String website, String address, String status) {
		
		authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		User user = new User(firstname, lastname, gender, dob,email, phonenumber, website, address, status);
	
		Response response = RestClient.doPost("JSON", baseURL, basePath, authTokenMap, null, true, user);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		System.out.println("=======================================");
		
	}
	
	
}
