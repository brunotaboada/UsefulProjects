package com.db.integration;


import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.db.configuration.GeneralConfiguration;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GeneralConfiguration.class}, loader=AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UrlReportControllerRestAssuredWithSpringContextTest {
	
	@Autowired
    WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	@Test
	public void testGetQueryMaster() throws Exception {
		given()
		.when()
		.get("/widgetquery/widgetid/{widgetId}","18")
		.then()
		.statusCode(200)
		//.body("listWidgetQueryParameters[0].id", equalTo("32"))
		.body("listWidgetQueryParameters", notNullValue())
		.log()
		.body();
	}
	
	@Test
	public void testGetQueriesNonMaster() throws Exception {
		given()
		.when()
		.get("/widgetquery/widgetid/{widgetId}","4")
		.then()
		.statusCode(200)
		.body("listWidgetQueryParameters[0].id", anything())
		.log()
		.body();
	}
	
}
