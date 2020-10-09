/**

 * Copyright 2019 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.TenantAPI.TestCase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amwell.TenantAPI.Pages.BasePage;
import com.amwell.TenantAPI.Pages.TenantService;
import com.amwell.TenantAPI.Pages.TenantApiPutDataPage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Tenant API Service Test Class
 * This Class contains basic autoamted test cases for Tenenat Service API
 * @see <a href="https://amwell.atlassian.net/browse/OC-50434">New Tenant Service to Support SOA and Jeremy</a>
 * @author MD.Rasul
 */

//public class WebTechCheck_Test extends BaseTest

public class TenantAPITest {
	private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	
	@BeforeMethod
	public void init() {
	}

	@AfterMethod
	public void ramDown() {
	}


	@Test(enabled = true)
	public void postTest() throws IOException {
		
		TenantService postdata = new TenantService();

//		Assert.assertTrue(postdata.createTenantUsingPostMethod(),"Faild to vairify posting StatusCode... ");
//		BasePage basePage = new BasePage();
//		basePage.authenticateUser();
	//	basePage.getToken("https://keycloak.dev.americanwell.com/auth/realms/dev-realm/protocol/openid-connect/token", "soa-tenant-service-client", "e4f108fe-7199-4c85-961c-932024fb8994", "soauser", "admin", "service/tenant/tenant.admin");
	
	}
	

	@Test(enabled = false)
	
	public void getTest() {
		
	
		
	
	}
	
//	@Test(enabled = false)
//	public void putTest() {
//		
//		RestAssured.baseURI = BASE_URL;
//		Response response = null;
//		
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("name", "MD RASULS");
//		requestParams.put("id", "RS1234567");
//		requestParams.put("enterpriseUri", "http://abc.com");
//		requestParams.put("callbackurl", "http://def.com");
//		
//		try {
//			response = RestAssured.given()
//					.contentType(ContentType.JSON)
//					.body(requestParams.toJSONString())
//					.put("api/v1/tenant/MDRASULS-1244180039");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("Response :" + response.asString());
//		System.out.println("Status Code :" + response.getStatusCode());
//		System.out.println("Does Reponse contains md Rasuls'? :" + response.asString().contains("MD RASULS"));
//		
//		
//		assertEquals(202, response.getStatusCode());
//	}
//	
//	
//	@Test(enabled = false)
//	public void getallDataStatus() throws IOException {
//	
//		
//
//		
//		
//	}
//




}
	
