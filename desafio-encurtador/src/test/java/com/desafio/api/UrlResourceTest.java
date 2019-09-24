package com.desafio.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desafio.DesafioEncurtadorApplicationTests;
import com.desafio.api.utils.Constants;
import com.desafio.api.utils.DesafioUtils;

@SpringBootTest
public class UrlResourceTest extends DesafioEncurtadorApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private UrlTestHelper helper;
	
	@Autowired
	private UrlResource resource;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(resource).build();
	}
	
	@Test
	public void testCreateShortenerUrl() throws Exception {
		Url novaUrl = build();
		this.mockMvc.perform(MockMvcRequestBuilders
			.post("/"+Constants.CONTROLLER_NAME)
			.contentType(Constants.APPLICATION_JSON)
			.content(DesafioUtils.convertObjectToJson(novaUrl)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testCreateAndOpenShortenerUrl() throws Exception {
		Url novaUrl = build();
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders
				.post("/"+Constants.CONTROLLER_NAME)
				.contentType(Constants.APPLICATION_JSON)
				.content(DesafioUtils.convertObjectToJson(novaUrl)))
			.andExpect(MockMvcResultMatchers.status().isOk());

		MvcResult mvcReturn = result.andReturn();
		
		String jsonString = mvcReturn.getResponse().getContentAsString();
		Url urlCreated = DesafioUtils.convertJsonStringToObject(jsonString);
		testOpenShortenerUrl(urlCreated.getUrlShort());
		
	}
	
	@Test
	public void testOpenShortenerUrlWithError() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/"+Constants.CONTROLLER_NAME+"/6IoM1"))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	public void testOpenShortenerUrl(String urlShort) throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get(urlShort))
					.andExpect(MockMvcResultMatchers.status().isSeeOther());
	}
	
    public Url build() {
        return helper.buildUrl();
    }
	
}
