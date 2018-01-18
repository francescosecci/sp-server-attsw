package com.pufose.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class MySqlImplementor implements IServiceImplementor {

	@Autowired
	private MysqlRepository repo;
	

	@Override
	public List<String> getAllId() {
		Iterable<DatabaseGrid> allGrids = repo.findAll();
		List<String> allid=new ArrayList<String>();
		allGrids.forEach(grid -> allid.add(""+grid.getId()));
		return allid;
	}

	@Override
	public DatabaseGrid getById(int id) {

		return repo.findOne(new Long(id));
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
		Iterable<DatabaseGrid> allGrids = repo.findAll();
		List<DatabaseGrid> casted=(List<DatabaseGrid>)(allGrids);
		if(casted.isEmpty()) return 1;
		int maxid=casted.get(casted.size()-1).getId();
		return maxid + 1;
	}

	@Override
	public void dropTable(int id) {
		// TODO Auto-generated method stub
		
	}

}
