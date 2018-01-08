package com.pufose.server;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=ARestController.class)
public class ARestControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private IGraphService graphService;
	
	@Test
	public void testStatus200() throws Exception{
		mvc.perform(get("/")).andExpect(status().isOk());
	}
	@Test
	public void testApi() throws Exception{
		given(graphService.getAllNames()).
			willReturn(Arrays.asList(
					"0","1"
					));
		this.mvc.perform(get("/api")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0]",is("0")))
			.andExpect(jsonPath("$[1]",is("1")));
		verify(graphService,times(1)).getAllNames();
	}
	@Test
	public void testPath() throws Exception{
		given(graphService.getShortestPath("0_0","0_2", 0)).
			willReturn(Arrays.asList(
					"0_0","0_1","0_2"
					));
		this.mvc.perform(get("/api/path0_0TO0_2IN0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]",is("0_0")))
				.andExpect(jsonPath("$[1]",is("0_1")))
				.andExpect(jsonPath("$[2]",is("0_2")));
		verify(graphService,times(1)).getShortestPath("0_0", "0_2", 0);
	}

}
