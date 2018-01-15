package com.pufose.server;


import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers=AWebController.class)
@Import(WebSecurityConfig.class)
public class AWebControllerTest  {
	
	@Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilter;

    protected MockMvc mockMvc;
    protected MockHttpSession session;
    
    @Before
    public void setup() throws Exception{
        this.session = new MockHttpSession();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(springSecurityFilter)
                .build();
    }

	@MockBean
	private IGridService gridService;
	
	@Test
	public void testLogin401() throws Exception {
		
		mockMvc.perform(get("/")).andExpect(status().is(401));
	}
	@Test
	public void testLoginOk() throws Exception{
		mockMvc.perform(get("/").with(httpBasic("user","password"))).andExpect(
				status().isOk());
	}
	@Test
	public void testHomepage() throws Exception {
		mockMvc.perform(get("/").with(httpBasic("user","password"))).
			andExpect(view().name("database"));
			
	}
	@Test
	public void testViewDb() throws Exception{
		mockMvc.perform(get("/viewdb").with(httpBasic("user","password"))).andExpect(
				status().isOk()).
				andExpect(view().name("dbview")).
				andExpect(model().attribute("allGrids", new ArrayList<>()));
		verify(gridService,times(1)).getAllGrids();
	}
	@Test
	public void testViewDbWhenThereIsOneGrid() throws Exception{
		given(gridService.getAllGrids()).willReturn(Arrays.asList(new DatabaseGrid()));
		mockMvc.perform(get("/viewdb").with(httpBasic("user","password"))).andExpect(
				status().isOk()).
				andExpect(view().name("dbview")).
				andExpect(model().attribute("allGrids", Arrays.asList(new DatabaseGrid())));
		verify(gridService,times(1)).getAllGrids();
	}
	@Test
	public void testViewDbWhenThereAreMultipleGrids() throws Exception{
		given(gridService.getAllGrids()).willReturn(Arrays.asList(new DatabaseGrid(),new DatabaseGrid()));
		mockMvc.perform(get("/viewdb").with(httpBasic("user","password"))).andExpect(
				status().isOk()).
				andExpect(view().name("dbview")).
				andExpect(model().attribute("allGrids", Arrays.asList(new DatabaseGrid(),new DatabaseGrid())));
		verify(gridService,times(1)).getAllGrids();
	}
	@Test
	public void testAddTableRendering() throws Exception {
		
		mockMvc.perform(get("/addtable").with(httpBasic("user","password"))).andExpect(
				status().isOk()).
				andExpect(view().name("tableadd")).
				andExpect(model().attribute("usercontent",isA(UserContent.class))).
				andExpect(model().attributeDoesNotExist("errormessage"));
	}
	@Test
	public void testAddOneTable() throws Exception {

		mockMvc.perform(post("/addtable").with(httpBasic("user","password")).
				param("content","1010").
				param("n","2")).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/"));
		verify(gridService,times(1)).nextId();
		int[][] expmat=new int[][] {{1,0},{1,0}};
		ArgumentCaptor<DatabaseGrid> arg=ArgumentCaptor.forClass(DatabaseGrid.class);
		verify(gridService,times(1)).storeInDb(arg.capture());
		assertEquals(new DatabaseGrid(expmat,0),arg.getValue());
		
	}
	@Test
	public void testAddOneTableWhenMissingContent() throws Exception {
		
		mockMvc.perform(post("/addtable").with(httpBasic("user","password")).
				param("content","1010").
				param("n","3")).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/"));
		verify(gridService,times(1)).nextId();
		int[][] expmat=new int[][] {{1,0,1},{0,0,0},{0,0,0}};
		ArgumentCaptor<DatabaseGrid> arg=ArgumentCaptor.forClass(DatabaseGrid.class);
		verify(gridService,times(1)).storeInDb(arg.capture());
		assertEquals(new DatabaseGrid(expmat,0),arg.getValue());
		
	}
	@Test
	public void testAddOneTableWhenTooContent() throws Exception {
	
		mockMvc.perform(post("/addtable").with(httpBasic("user","password")).
				param("content","11101010101010").
				param("n","2")).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/"));
		verify(gridService,times(1)).nextId();
		int[][] expmat=new int[][] {{1,1},{1,0}};
		ArgumentCaptor<DatabaseGrid> arg=ArgumentCaptor.forClass(DatabaseGrid.class);
		verify(gridService,times(1)).storeInDb(arg.capture());
		assertEquals(new DatabaseGrid(expmat,0),arg.getValue());
		
	}
	@Test
	public void testAddOneTableWhenWrongContent() throws Exception {
	
		mockMvc.perform(post("/addtable").with(httpBasic("user","password")).
				param("content","pippopluto").
				param("n","2")).
				andExpect(status().is3xxRedirection()).
				andExpect(view().name("redirect:/"));
		verify(gridService,times(1)).nextId();
		int[][] expmat=new int[][] {{0,0},{0,0}};
		ArgumentCaptor<DatabaseGrid> arg=ArgumentCaptor.forClass(DatabaseGrid.class);
		verify(gridService,times(1)).storeInDb(arg.capture());
		assertEquals(new DatabaseGrid(expmat,0),arg.getValue());
		
	}
	@Test
	public void testAddOneTableWhenWrongMatrixSize() throws Exception {
	
		mockMvc.perform(post("/addtable").with(httpBasic("user","password")).
				param("content","").
				param("n","-2")).
				andExpect(status().is2xxSuccessful()).
				andExpect(view().name("tableadd"))
				.andExpect(model().attributeExists("errormessage"));
		verify(gridService,times(0)).nextId();
		verify(gridService,times(0)).storeInDb(anyObject());
		
	}
	@Test
	public void testRemoveTableRendering() throws Exception {
		
		mockMvc.perform(get("/remtable").with(httpBasic("user","password"))).andExpect(
				status().isOk()).
				andExpect(view().name("tablerem")).
				andExpect(model().attribute("usercontent",isA(UserContent.class)));
	}
	@Test
	public void testRemoveOneTable() throws Exception {
		
		
		mockMvc.perform(post("/remtable").with(httpBasic("user","password")).
				param("content","").
				param("n","0")).
				andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
		verify(gridService,times(1)).dropTable(0);
		
		
	}
	
	
	
}
