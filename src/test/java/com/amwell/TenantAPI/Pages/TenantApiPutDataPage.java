package com.amwell.TenantAPI.Pages;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TenantApiPutDataPage extends BasePage {

	

 public TenantApiPutDataPage() throws IOException {
		super();
	}


 public boolean varifyPutData() {
		
		try {
			//putApiData(prop.getProperty("postBaseResUrl"), Integer.parseInt(prop.getProperty("postStatus")));
			putApiData("api/v1/tenant/TenantID-OYhZHdTKpa", 202);
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("invaid Data");
		}
		
	}

}
