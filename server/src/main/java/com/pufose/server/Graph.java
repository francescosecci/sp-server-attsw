package com.pufose.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
	
	public List<String> getNodes(){
		return new LinkedList<String>(nodes);
	}
	
	public int getN() {
		return nodes.size();
	}
	public int getL() {
		return edges.size();
	}
	
	public List<String> minPath(String from, String to) {
		Map<String,Boolean> vis=new HashMap<String,Boolean>();
		Map<String,String> prev=new HashMap<String,String>();
		List<String> minPath=new LinkedList<String>();
		Queue<String> queue= new LinkedList<String>();
		String current=from;
		queue.add(current);
		vis.put(current, true);
		
		while(!queue.isEmpty()) {
			current=queue.remove();
			if(current.equals(to)) break;
			else {
				for(String neigh:neighboursOf(current)) {
					if(!vis.containsKey(neigh)) {
						queue.add(neigh);
						vis.put(neigh,true);
						prev.put(neigh,current);
					}
				}
			}
		}
		
		if(!current.equals(to)) {
			return new LinkedList<String>();
		}
		for(String node=to; node!=null; node=prev.get(node)) {
			minPath.add(node);
		}
		Collections.reverse(minPath);
		return minPath;
	}
	
	private String[] neighboursOf(String target) {
		String neighbours="";
		for(String[] e:edges) {
			if(e[0].equals(target)) {
				neighbours+=e[1]+" ";
			}
		}
		return neighbours.split(" ");
	}

}
