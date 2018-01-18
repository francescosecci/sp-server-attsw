package com.pufose.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class IGridService {
	
	@Autowired
	private IServiceImplementor impl;
	
	
	public void setImplementor(IServiceImplementor impl) {
		this.impl=impl;
	}
	
	public final List<String> getShortestPath(String from, String to, int id){
		return impl.getShortestPath(from,to,id);
	}
	public final List<String> getAllId(){
		return impl.getAllId();
	}
	public final DatabaseGrid getById(int id) {
		return impl.getById(id);
	}
	public final void storeInDb(DatabaseGrid grid) {
		impl.storeInDb(grid);
	}
	public final List<DatabaseGrid> getAllGrids(){
		return impl.getAllGrids();
	}
	public final int nextId() {
		return impl.nextId();
	}
	public final void dropTable(int id) {
		impl.dropTable(id);
	}

}
