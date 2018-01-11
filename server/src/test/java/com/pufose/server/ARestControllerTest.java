package com.pufose.server;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=ARestController.class)
@Import(WebSecurityConfig.class)
public class ARestControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private IGridService gridService;
	
	@Test
	public void testStatus200() throws Exception{
		mvc.perform(get("/api")).andExpect(status().isOk());
	}
	@Test
	public void testGetAllNamesWhenNoGridExists() throws Exception {
		whenGetAllNamesReturn(gridService,Arrays.asList());
		this.mvc.perform(get("/api")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(0)));
				
			verify(gridService,times(1)).getAllId();
		
	}
	private void whenGetAllNamesReturn(IGridService gs,List<String> toreturn) {
		given(gs.getAllId()).willReturn(toreturn);
	}
	@Test
	public void testApiWhenSingleGridExists() throws Exception {
		whenGetAllNamesReturn(gridService,Arrays.asList("0"));
		this.mvc.perform(get("/api")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]",is("0")));
				
			verify(gridService,times(1)).getAllId();
	}
	
	@Test
	public void testApiWhenMoreThanOneGridExists() throws Exception{
		whenGetAllNamesReturn(gridService,Arrays.asList("0","1"));
		this.mvc.perform(get("/api")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0]",is("0")))
			.andExpect(jsonPath("$[1]",is("1")));
		verify(gridService,times(1)).getAllId();
	}
	private void whenPathReturn(IGridService gs,String from, String to, int id,List<String> toreturn) {
		given(gs.getShortestPath(from, to, id)).
			willReturn(toreturn);
	}
	@Test
	public void testPathWhenOneNode() throws Exception{
		whenPathReturn(gridService,"0_0","0_2",0,Arrays.asList("0"));
		this.mvc.perform(get("/api/path0_0TO0_2IN0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]",is("0")));
		verify(gridService,times(1)).getShortestPath("0_0", "0_2", 0);
	}
	@Test
	public void testPathWhenNoNodes() throws Exception{
		whenPathReturn(gridService,"0_0","0_2",0,Arrays.asList());
		this.mvc.perform(get("/api/path0_0TO0_2IN0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(0)));
		verify(gridService,times(1)).getShortestPath("0_0", "0_2", 0);
	}
	
	@Test
	public void testPathWhenMoreThanOneNode() throws Exception{
		whenPathReturn(gridService,"0_0","0_2",0,Arrays.asList("0_0","0_1","0_2"));
		
		this.mvc.perform(get("/api/path0_0TO0_2IN0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0]",is("0_0")))
				.andExpect(jsonPath("$[1]",is("0_1")))
				.andExpect(jsonPath("$[2]",is("0_2")));
		verify(gridService,times(1)).getShortestPath("0_0", "0_2", 0);
	}
	
	
	
	@Test
	public void testGrid() throws Exception{
		int[][] mat=new int[][] {};
		given(gridService.getById(0)).
			willReturn(new DatabaseGrid(mat,0));
		
		this.mvc.perform(get("/api/grid0")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("n",is(0)))
			.andExpect(jsonPath("matrix",hasSize(0)))
			.andExpect(jsonPath("id",is(0)));
			
		verify(gridService,times(1)).getById(0);
	}
	
	@Test
	public void testGridWhenComplexMatrix() throws Exception{
		int[][] mat=new int[][] {
			{1,1},
			{0,1}
		};
		given(gridService.getById(1)).
			willReturn(new DatabaseGrid(mat,1));
		
		this.mvc.perform(get("/api/grid1")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("n",is(2)))
			.andExpect(jsonPath("matrix",hasSize(2)))
			.andExpect(jsonPath("id",is(1)));
			
		verify(gridService,times(1)).getById(1);
	}
	@Test
	public void testGridWhenNotExists() throws Exception{
		given(gridService.getById(1)).
		willReturn(null);
	
	MvcResult res =this.mvc.perform(get("/api/grid1")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
		assertEquals("null",res.getResponse().getContentAsString());
	verify(gridService,times(1)).getById(1);
	}
	
}
