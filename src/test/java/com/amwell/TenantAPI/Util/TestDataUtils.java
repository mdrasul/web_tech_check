package com.amwell.TenantAPI.Util;

import org.apache.commons.lang3.RandomStringUtils;

import com.amwell.TenantAPI.Model.Tenant;
import com.google.common.base.CharMatcher;

public class TestDataUtils {

	public static Tenant createOneSampleTenant() {
		Tenant tenant = new Tenant();
		tenant.setId("TenantID-" + RandomStringUtils.randomAlphabetic(10));
		tenant.setName("TenantName" + RandomStringUtils.randomAlphabetic(20));
		tenant.setEnterpriseUri("http://abc.com");
		tenant.setCallbackUrl("http://desf.com");
		return tenant;
	}
	
}
