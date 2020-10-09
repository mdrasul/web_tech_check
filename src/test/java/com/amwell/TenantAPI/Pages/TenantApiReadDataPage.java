package com.amwell.TenantAPI.Pages;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TenantApiReadDataPage extends BasePage {

	private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public TenantApiReadDataPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean getTenantStatusCodeUsingGetMethod(String endPram) {
		
		Response response = tenantGetMethod("TenantID-svJQROLlHP");
		try {
			
			if (response.getStatusCode() == 200) {
				log.info("Expected StatusCode : 200" + "Actual StatusCode : " + response.getStatusCode());
				return true;
			} else {
				throw new RuntimeException(
						"Expected StatusCode is 200" + " But actual StatusCode is : " + response.statusCode());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean getTenantAlldataStatusCodeUsingGetMethod() {

		Response response = tenantAllDataGetMethod("api/v1/tenant");
		try {

			if (response.getStatusCode() == 200) {
				log.info("Expected StatusCode : 200" + "Actual StatusCode : " + response.getStatusCode());
				return true;
			} else {
				throw new RuntimeException(
						"Expected StatusCode is 200" + " But actual StatusCode is : " + response.statusCode());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean getTenantVerifyDataUsingGetMethod(String endPram) {
		
		Response response = tenantGetMethod("TenantID-svJQROLlHP");
		String responseBody = response.getBody().asString();
		
		System.out.println(responseBody);
		JsonPath js = new JsonPath(responseBody);
		String varifyId = js.get("id");
		String varifyName = js.getString("name");
		
		try {
			
			if (varifyId.contentEquals("TenantID-svJQROLlHP")
					&& varifyName.contentEquals("TenantNamebIRbjBgTOXlLsDzamVGe")) {
				log.info("Expected id : TenantID-svJQROLlHP" + "Actual id : " + varifyId);
				log.info("Expected name : TenantNamebIRbjBgTOXlLsDzamVGe" + "Actual name : " + varifyName);
				return true;
			} else {
				throw new RuntimeException("Data dont match");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public void getTenantVerifyAllDataBodyGetMethod() {
		
		
		
		Response response = tenantAllDataGetMethod("api/v1/tenant/");
		System.out.println(response.statusCode());
		String responseBody = response.getBody().asString();
		System.out.println("body is :"+responseBody);
		//JSONObject objects = new JSONObject (responseBody);
		
		//System.out.println(objects);
//		try {
//			
//			if (varifyId.contentEquals(id)
//					&& varifyName.contentEquals(name)) {
//				log.info("Expected id : "+ id + "Actual id : " + varifyId);
//				log.info("Expected name :"+ name + "Actual name : " + varifyName);
//				return true;
//			} else {
//				throw new RuntimeException("Data dont match");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
			
		
		
	}
//	public boolean getTenantVerifyDataCountUsingGetMethod() {
//
//		Response response = tenantGetMethod("TenantID-svJQROLlHP");
//		String responseBody = response.getBody().asString();
//		JsonPath pathResponse = response.jsonPath();
//		List<String> key = pathResponse.getList("id")
//	
//	
//		    
//
//
//		try {
//			if (js.) {
//				
//				log.info("Expected name : TenantNamebIRbjBgTOXlLsDzamVGe" + "Actual name : " );
//				return true;
//			} else {
//				throw new RuntimeException(
//						"Expected StatusCode is 200" + " But actual StatusCode is : " + response.statusCode());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//	}

}
