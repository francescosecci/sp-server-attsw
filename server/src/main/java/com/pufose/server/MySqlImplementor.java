package com.pufose.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class MySqlImplementor implements IServiceImplementor {

	@Autowired
	private MysqlRepository repo;
	

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
