package com.pufose.server;

import java.util.List;

import org.springframework.context.annotation.Primary;

@Primary
public class MongoImplementor implements IServiceImplementor {

	@Override
	public List<String> getShortestPath(String from, String to, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllId() {
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

	@Override
	public int nextId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dropTable(int id) {
		// TODO Auto-generated method stub
		
	}

}
