package com.pufose.server;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GraphTest {

	private Graph g = new Graph();
	private String node1 = "nodo1";
	private String node2 = "nodo2";
	private String node3 = "nodo3";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected = IllegalArgumentException.class)
	public void addEdgeWhenFirstNodeIsNotInListTest() {
		g.addNodes(node1);
		g.addNodes(node2);
		g.addEdge(node3, node1);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Node nodo3 does not exists");
	}
	

}
