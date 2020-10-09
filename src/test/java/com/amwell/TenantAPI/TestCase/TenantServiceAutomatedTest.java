package com.amwell.TenantAPI.TestCase;

import static org.testng.Assert.assertNotNull;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amwell.TenantAPI.Model.Tenant;
import com.amwell.TenantAPI.Pages.TenantApiPutDataPage;
import com.amwell.TenantAPI.Pages.TenantApiReadDataPage;
import com.amwell.TenantAPI.Pages.TenantService;
import com.amwell.TenantAPI.Util.TestDataUtils;

public class TenantServiceAutomatedTest {
	
	
	public static final String BASE_URL="http://jeremy-bigrio-02-int.dev.americanwell.com:8094";
	
	
    /**
     * Verify that the Tenant Management Post (Create a new Tenant ) endpoint is working as expected
    */
	
	@Test(enabled = true)
	public void crateTenantUsingPostMethod() throws IOException {
		Tenant sampleTenant = TestDataUtils.createOneSampleTenant();
		assertNotNull(sampleTenant.getId(), "Failed to generate a sample tenant... !!");
		
		TenantService tenantService = new TenantService();
		Assert.assertTrue(tenantService.createTenantUsingPostMethod(sampleTenant),"Faild to vairify Creating Tenant Using Post Methos..");
	}
	
	/**
     * Verify that the Tenant Management Put (Update Tenant Info) endpoint is working as expected
    */
	@Test(enabled = true)
	public void updateTenantInfoUsingPutMethod() throws IOException {
	
		TenantApiPutDataPage apiPutDataPage =new TenantApiPutDataPage();
		
		// Update Tenants Info and check status
		 Assert.assertTrue(apiPutDataPage.varifyPutData(),"Faild to Update Tenant Info Using Put Methos..");
		
	}
	
	/**
     * Verify that the Tenant Management get (get Tenant Info) endpoint is working as expected
    */
	@Test(enabled = true)
	public void getTenantInfoUsingGetMethod() throws IOException {
		TenantApiReadDataPage apiReadDataPage =new TenantApiReadDataPage();
		apiReadDataPage.getTenantVerifyAllDataBodyGetMethod();
		
		// Tenant get Status
		Assert.assertTrue(apiReadDataPage.getTenantStatusCodeUsingGetMethod("TenantID-svJQROLlHP"), "Faild to vairify get StatusCode..");
		
		// Tenant varify Body
		Assert.assertTrue(apiReadDataPage.getTenantVerifyDataUsingGetMethod("TenantID-svJQROLlHP"), "Faild to vairify correcnt Body in get call..");
		
		// Tenant all Data Status
		Assert.assertTrue(apiReadDataPage.getTenantAlldataStatusCodeUsingGetMethod(), "Faild to vairify Get all data Statuscode..");
		
	
		
	}
	
	
	
}
