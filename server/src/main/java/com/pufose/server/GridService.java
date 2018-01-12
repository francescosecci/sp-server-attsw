package com.pufose.server;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridService implements IGridService {

	@Autowired
	private IGridRepository repository;
	private static final String D_D = "%d_%d";
	@Autowired
	private Graph tobuild;
	@Override
	public List<String> getAllId() {

		List<DatabaseGrid> list = repository.findAll();
		List<String> toreturn = new LinkedList<>();
		for (DatabaseGrid grid : list)
			toreturn.add("" + grid.getId());
		return toreturn;

	}

	@Override
	public List<String> getShortestPath(String from, String to, int id) {
		
		
		DatabaseGrid grid=repository.findById(id);
		int n=grid.getN();
		for(int i=0; i<n;i++) {
			for(int j=0; j<n;j++) {
				if(grid.isEnabled(i, j)) {
					
					tobuild.addNodes(grid.getName(i, j));
				}
			}
		}
		for(String node:tobuild.getNodes()) {
		
			int i=node.charAt(0)-48;
			int j=node.charAt(2)-48;
			
			if(grid.isEnabled(i+1,j) ) {
				String target=String.format(D_D, i+1,j);
				tobuild.addEdge(node, target);
			}
			if(grid.isEnabled(i-1, j) ) {
				String target=String.format(D_D, i-1,j);		
				tobuild.addEdge(node,target);
			}
			if(grid.isEnabled(i, j+1)) {
				String target=String.format(D_D, i,j+1);
				tobuild.addEdge(node,target);
			}
			if(grid.isEnabled(i, j-1)) {
				String target=String.format(D_D, i,j-1);
				tobuild.addEdge(node, target);
			}
		}
		return tobuild.minPath(from,to);
}

	@Override
	public DatabaseGrid getById(int id) {
		return repository.findById(id);
	}

	@Override
	public void storeInDb(DatabaseGrid grid) {
		repository.save(grid);

	}

	@Override
	public List<DatabaseGrid> getAllGrids() {
	
		return repository.findAll();
	}

	public int nextId() {
		List<DatabaseGrid> allGrids = repository.findAll();
		if(allGrids.isEmpty()) return 1;
		int maxid=allGrids.get(allGrids.size()-1).getId();
		return maxid + 1;
	}

	public void dropTable(int id) {
		repository.delete(id);
		
	}

	

}
