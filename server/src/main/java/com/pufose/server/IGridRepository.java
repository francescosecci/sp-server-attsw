package com.pufose.server;

import java.util.List;

public interface IGridRepository {

	public List<DatabaseGrid> findAll();

	public DatabaseGrid findById(int id);
}
