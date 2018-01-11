package com.pufose.server;

import static org.junit.Assert.*;

import java.util.List;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GridServiceAndRepositoryIT {

	@Autowired
	private IGridRepository repo;
	@Autowired
	private GridService service;
	
	@Test
	public void testGetAllIdWhenDbEmpty() {
		assertEquals(0,service.getAllId().size());
	}
	@Before
	public void setUp() {
		//CREATE FONGO DB
		repo.deleteAll();
	}
	@Test
	public void testGetAllIdWhenDbContainsOne() {
		repo.save(new DatabaseGrid(0));
		List<String> ids=service.getAllId();
		assertEquals(1,ids.size());
		assertEquals("0",ids.get(0));
	}
	@Test
	public void testGetAllIdWhenDbContainsMore() {
		repo.save(new DatabaseGrid(0));
		repo.save(new DatabaseGrid(1));
		List<String> ids=service.getAllId();
		assertEquals(2,ids.size());
		assertEquals("0",ids.get(0));
		assertEquals("1",ids.get(1));
	}
	@Test
	public void testGetByIdWhenExists() {
		DatabaseGrid expected;
		expected=repo.save(new DatabaseGrid(0));
		assertEquals(expected,service.getById(0));
	}
	@Test
	public void testGetByIdWhenNotExists() {
		assertNull(service.getById(0));
	}
	@Test
	public void testGetShortestPathWhenIsEmpty() {
		repo.save(new DatabaseGrid(10));
		List<String> path=service.getShortestPath("0", "0", 10);
		assertEquals(0,path.size());
		
	}
}
