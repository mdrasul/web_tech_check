package com.amwell.TenantAPI.Pages;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amwell.TenantAPI.Model.Tenant;
import com.amwell.TenantAPI.Util.TestDataUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class BasePage {
	
	public static Properties prop;
	
public BasePage() throws IOException {
	
	prop = new Properties();
	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/BasicTenantAPIInputData/TenantApiAsset.properties");
	prop.load(ip);
	
}
	
	
	/** ##### Static variable dedecaration ##### */
	
	private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	public static final String BASE_URL="http://jeremy-bigrio-02-int.dev.americanwell.com:8094";
	public static final String jsonFileLocation = System.getProperty("user.dir") +"\\src\\test\\resources\\BasicTenantAPIInputData\\tenant.json";

	
	/** ##### Reusable Service Methds ##### */

//	public Response postApiData(String baseurl) {
//		Response response = null;
//		try {
//
//			RestAssured.baseURI = BASE_URL;
//			String jsonBody = readJsonFile(jsonFileLocation);
//			String accessToken = getAccessTokenForAdmin();
//
//			RequestSpecification requestSpec = RestAssured.given();
//			requestSpec.auth().preemptive().oauth2(accessToken);
//			requestSpec.contentType("application/json");
//
//			requestSpec.body(jsonBody);
//			response = requestSpec.post(baseurl);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}
	public Response tenantGetMethod(String endPram) {
		Response response = null;
		try {
			RestAssured.baseURI = BASE_URL;
			RequestSpecification httpRequest = RestAssured.given();
			response = httpRequest.get("api/v1/tenant/" + endPram);
			System.out.println("StatusCode is : "+response.statusCode());		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return	response;
	}


	public Response tenantAllDataGetMethod(String basePath) {
		Response response = null;
		try {
			RestAssured.baseURI = BASE_URL;
			RequestSpecification httpRequest = RestAssured.given();
			response = httpRequest.get(basePath);				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;	
	}

	public void putApiData(String baseurl,int statusCode) {	 
		 String accessToken=getAccessTokenForAdmin();
		 try {	
			RestAssured.baseURI =BASE_URL;	  
			RequestSpecification request = RestAssured.given();
	    	String jsonBody = readJsonFile(jsonFileLocation);
	    	request.auth().preemptive().oauth2(accessToken);
	    	request.body(jsonBody);
	    	request.contentType(ContentType.JSON);
	 		
	    	Response response = request.put(baseurl);
	 		
	    	System.out.println(response.getStatusCode());
	 		Assert.assertEquals(response.getStatusCode(), statusCode);
	 		//String successCode = response.jsonPath().get("SuccessCode");
	 		//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	     } catch (Exception e) {
	         e.printStackTrace();
	     }    
	 }

	/** ##### Reusable Page helper Methored ##### */

	public String readJsonFile(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

	@SuppressWarnings({ "unused", "deprecation" })
	public String getAccessTokenForAdmin() {    
		String response =
				given()
				.parameters("username", "soauser",
						"password", "admin", 
						"grant_type", "password",
						"scope", "service/tenant/tenant.admin", 
						"client_id", "soa-tenant-service-client",
						"client_secret", "e4f108fe-7199-4c85-961c-932024fb8994")
				.auth()
				.preemptive()
				.basic("soa-tenant-service-client","e4f108fe-7199-4c85-961c-932024fb8994")
				.when()
				.post("https://keycloak.dev.americanwell.com/auth/realms/dev-realm/protocol/openid-connect/token")
				.asString();
		
		JsonPath jsonPath = new JsonPath(response);
		String  accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);
		return accessToken;
	}
	@SuppressWarnings("deprecation")
	
	public String getAccessTokenForClient() {    
			String response =
		            given()
		                .parameters("username", "soauser",
		                			"password", "admin", 
		                           "grant_type", "client credentials",
		                           "scope", "service/tenant/tenant.admin", 
		                           "client_id", "soa-tenant-service-client",
		                           "client_secret", "e4f108fe-7199-4c85-961c-932024fb8994")
		                .auth()
		                .preemptive()
		                .basic("soa-tenant-service-client","e4f108fe-7199-4c85-961c-932024fb8994")
		            .when()
		                .post("https://keycloak.dev.americanwell.com/auth/realms/dev-realm/protocol/openid-connect/token")
		                .asString();
	
		  JsonPath jsonPath = new JsonPath(response);
		  String  accessToken = jsonPath.getString("access_token");
		  System.out.println(accessToken);
		  return accessToken;
		}
	
	
}
