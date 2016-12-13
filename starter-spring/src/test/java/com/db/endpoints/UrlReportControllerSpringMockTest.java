package com.db.endpoints;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UrlReportControllerSpringMockTest {

	/*@InjectMocks
	private UrlReportController urlreportController;*/

	/*@Mock
	private DbUiWidgetParamsService widgetParamsService;*/


	private MockMvc mockMvc;
	
	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);
		
		// Setup Spring test in standalone mode
		//this.mockMvc = MockMvcBuilders.standaloneSetup(urlreportController).build();

	}

	@Test
	public void testGetParametersByReportName() throws Exception {
		//when(widgetService.findByWidgetName(anyString())).thenReturn(wFixture);
		//when(widgetParamsService.findById(anyString())).thenReturn(listFixture);
		String jsonReturned = this.mockMvc.perform(get("/widgetparam/widgetname/{widgetName}", "test"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("listWidgetParameters[0].name", notNullValue()))
			.andReturn().getResponse().getContentAsString();
		System.out.println(jsonReturned);
	}
	
}
