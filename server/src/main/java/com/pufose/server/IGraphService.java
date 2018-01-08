package com.pufose.server;

import java.util.List;

public interface IGraphService {
	public List<String> getShortestPath(String from, String to, int id);
	public List<String> getAllNames();
	public DatabaseGrid getById(int id);
	public void storeInDb(DatabaseGrid grid);
	public List<DatabaseGrid> getAllGrids();


}