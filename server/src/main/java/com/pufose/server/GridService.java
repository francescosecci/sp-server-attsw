package com.pufose.server;

import java.util.LinkedList;
import java.util.List;

public class GridService implements IGridService {

	IGridRepository repository;
	
	@Override
	public List<String> getAllId() {
		
		List<DatabaseGrid> list=repository.findAll();
		List<String> toreturn = new LinkedList<String>();
		for(DatabaseGrid grid:list) toreturn.add(""+grid.getId());
		return toreturn;
		
}

	@Override
	public List<String> getShortestPath(String from, String to, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public DatabaseGrid getById(int id) {
		return repository.findById(id);
	}

	@Override
	public void storeInDb(DatabaseGrid grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DatabaseGrid> getAllGrids() {
		// TODO Auto-generated method stub
		return null;
	}

}
