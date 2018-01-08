package com.pufose.server;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	private List<String> nodes;
	private List<String[]> edges;
	
	public Graph() {
		nodes=new LinkedList<String>();
		edges=new LinkedList<String[]>();
	}
	
	public void addNodes(String name) {
		nodes.add(name);
	}

	public void addEdge(String node1, String node2) throws IllegalArgumentException {
		if(!containsNode(node1)) throw new IllegalArgumentException("Node "+node1+" does not exists");
	}

	public boolean containsNode(String name) {
		return nodes.contains(name);
	}

}
