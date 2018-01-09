package com.pufose.server;


import java.util.LinkedList;
import java.util.List;

public class GridService implements IGridService {

	private IGridRepository repository;
	
	
	private Graph tobuild;
	@Override
	public List<String> getAllId() {

		List<DatabaseGrid> list = repository.findAll();
		List<String> toreturn = new LinkedList<String>();
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
					
					tobuild.addNode(grid.getName(i, j));
				}
			}
		}
		for(String node:tobuild.getNodes()) {
		
			int i=node.charAt(0)-48;
			int j=node.charAt(2)-48;
			
			if(i<n-1 && grid.isEnabled(i+1,j) ) {
				String target=String.format("%d_%d", i+1,j);
				tobuild.addEdge(node, target);
			}
			if(i>0 && grid.isEnabled(i-1, j) ) {
				String target=String.format("%d_%d", i-1,j);		
				tobuild.addEdge(node,target);
			}
			if(j<n-1 && grid.isEnabled(i, j+1)) {
				String target=String.format("%d_%d", i,j+1);
				tobuild.addEdge(node,target);
			}
			if(j>0 && grid.isEnabled(i, j-1)) {
				String target=String.format("%d_%d", i,j-1);
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
		int maxid = 0;
		for (DatabaseGrid grid : allGrids) {
			if (grid.getId() > maxid)
				maxid = grid.getId();
		}
		return maxid + 1;
	}

	public void dropTable(DatabaseGrid grid) {
		repository.delete(grid);
		
	}

	

}
