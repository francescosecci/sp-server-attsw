package com.pufose.server;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class GraphService implements IGraphService {

	@Override
	public List<String> getShortestPath(String from, String to, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatabaseGrid getById(int id) {
		// TODO Auto-generated method stub
		return null;
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
