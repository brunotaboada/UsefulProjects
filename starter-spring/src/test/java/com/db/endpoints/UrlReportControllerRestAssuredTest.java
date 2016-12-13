package com.db.endpoints;


import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.db.resource.controller.urlreports.UrlReportController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

public class UrlReportControllerRestAssuredTest {
	
	@InjectMocks
	private UrlReportController urlreportController;

	//@Mock
	//private DbUiWidgetSourceService widgetService;
	
	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);
		RestAssuredMockMvc.standaloneSetup(urlreportController);
	}

	@Test
	public void testGetParametersByReportName() throws Exception {
		//when(widgetService.findByWidgetName(anyString())).thenReturn(wFixture);
		//when(widgetParamsService.findById(anyString())).thenReturn(listFixture);
		given()
		.when()
		.get("/widgetparam/widgetname/{widgetName}","Url Report Template")
		.then().body("listWidgetParameters", notNullValue()).log().body();
	}
	
}
