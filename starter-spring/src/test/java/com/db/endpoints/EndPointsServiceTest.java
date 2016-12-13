package com.db.endpoints;


import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

@Ignore
public class EndPointsServiceTest {

	private String baseUrl = "http://localhost:8080";

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testListWidgetParameters() {
		RestAssured.useRelaxedHTTPSValidation();
		when().get(baseUrl + "/entitlements-service/widget/{widgetId}/queries", "18").then().body("listWidgetQueryParameters", notNullValue());
	}

	@Test
	public void testGetParametersByReportName() {
		RestAssured.useRelaxedHTTPSValidation();
		when().get(baseUrl + "/entitlements-service/widget/{widgetName}/widgets", "Url Report Template").then().statusCode(200).body("listWidgetParameters", notNullValue()).log().body();
	}

	@Test
	public void testGetParametersByReportNameNull() {
		RestAssured.useRelaxedHTTPSValidation();
		when().get(baseUrl + "/entitlements-service/widget/{widgetName}/widgets", " ").then().statusCode(200).body("listWidgetParameters", nullValue());
	}

}
