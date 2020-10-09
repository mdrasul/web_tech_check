package com.amwell.TenantAPI.Pages;import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amwell.TenantAPI.Model.Tenant;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TenantService extends BasePage {

	
	private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//	public static final String BASE_URL="http://jeremy-bigrio-02-int.dev.americanwell.com:8094";
    ObjectMapper mapper = new ObjectMapper();

	
	// ### Constructor 
	public TenantService() throws IOException {
		super();
	}

	
	public boolean createTenantUsingPostMethod(Tenant sampleTenant) {
		Response response = null;
		try {
			RestAssured.baseURI = BASE_URL;
			String accessToken = getAccessTokenForAdmin();
	
			RequestSpecification requestSpec = RestAssured.given();
			requestSpec.auth().preemptive().oauth2(accessToken);
			requestSpec.contentType("application/json");
			requestSpec.body(mapper.writeValueAsString(sampleTenant));
			
			response = requestSpec.post("api/v1/tenant");
			System.out.println(response.getBody());
//			System.out.println("Status Code :- "+ response.getStatusCode());
//			System.out.println("OAuth Response - " + response.getBody().asString());
//		
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		// Return true / false based on response status..
		if (response.getStatusCode()== 202) {
			return true;
		}else {
			return false;
		}
	}


	public boolean updateTenantInfoUsingPutMethod(Tenant sampleTenant) {
		Response response = null;
		try {
			RestAssured.baseURI = BASE_URL;
			String accessToken = getAccessTokenForAdmin();

			RequestSpecification requestSpec = RestAssured.given();
			requestSpec.auth().preemptive().oauth2(accessToken);
			requestSpec.contentType("application/json");
			requestSpec.body(mapper.writeValueAsString(sampleTenant));

			System.out.println(requestSpec.log().toString());
			
			response = requestSpec.put("/api/v1/tenant/"+sampleTenant.getId());
			
//			    String complete = response.asString();
//			    JsonPath js = new JsonPath(complete);
//			    String s = js.get("id");
//			    System.out.println(s);
			System.out.println("Status Code :- "+ response.getStatusCode());
			System.out.println("OAuth Response - " + response.getBody().asString());
			
			System.out.println(response.asString());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// == >> Return true / false based on response status..
		if (response.getStatusCode()== 202) {
			return true;
		}else {
			return false;
		}
	}



 }
 
 

