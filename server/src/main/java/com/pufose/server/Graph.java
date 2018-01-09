package com.pufose.server;

import java.util.List;



public interface Graph {

		public List<String> minPath(String from, String to);

		public List<String> getNodes();
		
		public List<String[]> getEdges();
		
		public void addNode(String n);
		
		public void addEdge(String n1, String n2);
}
