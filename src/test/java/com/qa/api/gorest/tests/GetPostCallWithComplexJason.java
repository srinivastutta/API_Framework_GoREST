package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.complexpojo.Avator;
import com.qa.api.gorest.complexpojo.Edit;
import com.qa.api.gorest.complexpojo.Links;
import com.qa.api.gorest.complexpojo.Self;
import com.qa.api.gorest.complexpojo.User;
import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;


@Epic("GoREST API Create User feature implementation...")
@Feature("Creating User API feature...")
public class GetPostCallWithComplexJason {


public static String token = "BrLSg_8Ney2mmqS0aQzUavzGGBoDdiz5fAHU";

@Description("Creating user with Pojo Object")
@Severity(SeverityLevel.NORMAL)
	@Test
	public void CreateUser_WithComplexPojo() {
		Self sf= new Self("http://www.sf.com");
		Edit ed = new Edit("http://www.ed.com");
		Avator av = new Avator("http://www.av.com");
		
		Links ln = new Links(sf, ed, av);
		
		User user = new User("Mannu", "Tutta", "male", "01-01-1998",
		"mannu41@gmail.com", "1234123423", "http://mannu.com", "test address", "active", ln);
		
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("Authorization", "Bearer "+token);
		
		Response response = RestClient.doPost("JSON", "https://gorest.co.in", "/public-api/users", tokenMap, null, true, user);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
		Assert.assertEquals(response.statusCode(), 200);
		
	
	}
	}
