package com.pufose.server;

import java.util.List;

public interface IGridService {
	public List<String> getShortestPath(String from, String to, int id);
	public List<String> getAllId();
	public DatabaseGrid getById(int id);
	public void storeInDb(DatabaseGrid grid);
	public List<DatabaseGrid> getAllGrids();
	public int nextId();
	public void dropTable(int id);

}
