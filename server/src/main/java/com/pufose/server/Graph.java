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
		if(!containsNode(node2)) throw new IllegalArgumentException("Node "+node2+" does not exists");
		edges.add(new String[] {node1,node2});
	}

	public boolean containsNode(String name) {
		return nodes.contains(name);
	}

	public List<String[]> getEdges(){
		return new LinkedList<String[]>(edges);
	}

}
