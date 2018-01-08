package com.pufose.server;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GraphServiceTest {

	@Mock
	private IGridRepository gridRepository;
	
	@InjectMocks
	private GridService gridService;
	
	@Test
	public void GetAllIdWhenThereIsMoreThanOneGrid() {
		DatabaseGrid grid1=new DatabaseGrid(1);
		DatabaseGrid grid2=new DatabaseGrid(2);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1,grid2));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("1","2"));
		verify(gridRepository,times(1)).findAll();
	}
	
	@Test
	public void GetAllIdWhenThereIsOneGrid() {
		DatabaseGrid grid1=new DatabaseGrid(1);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("1"));
		verify(gridRepository,times(1)).findAll();
		
	}
	
	@Test
	public void getAllIdWhenThereIsNoGrid() {
		List<DatabaseGrid> grids = new ArrayList<>();
		given(gridRepository.findAll()).willReturn(Arrays.asList());
		assertThat(gridService.getAllId()).isEqualTo(grids);
		verify(gridRepository,times(1)).findAll();
		
		
	}
	
	@Test
	public void GetByIdWhenThereIsNoSuchGrid(){
		given(gridRepository.findById(7)).willReturn(null);
		assertThat(gridService.getById(7)).isEqualTo(null);
		verify(gridRepository,times(1)).findById(7);
			
	}
	
	@Test
	public void GetByIdWhenThereIsSuchGrid() {
		DatabaseGrid grid1=new DatabaseGrid(1);
		given(gridRepository.findById(1)).willReturn(grid1);
		assertThat(gridService.getById(1)).isEqualTo(grid1);
		verify(gridRepository,times(1)).findById(1);
		
	}
	
	@Test
	public void StoreInDBSaveIsCalled() {
		DatabaseGrid grid1=new DatabaseGrid(1);
		given(gridRepository.save(grid1)).willReturn(null);
		gridService.storeInDb(grid1);
		verify(gridRepository,times(1)).save(grid1);
		
	}
	
	@Test
	public void NextIdWhenThereIsNoGrid() {
		given(gridRepository.findAll()).willReturn(new ArrayList<>());
		assertThat(gridService.nextId()).isEqualTo(1);
		verify(gridRepository,times(1)).findAll();
		
	}
	
	@Test
	public void NextIdWhenThereAreGrids() {
		DatabaseGrid grid1=new DatabaseGrid(1);
		DatabaseGrid grid2=new DatabaseGrid(2);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1,grid2));
		assertThat(gridService.nextId()).isEqualTo(3);
		verify(gridRepository,times(1)).findAll();
	}
	
	@Test
	public void GetAllGridsWhenThereIsNoGrids() {
		List<DatabaseGrid> grids = new ArrayList<>();
		given(gridRepository.findAll()).willReturn(new ArrayList<>());
		assertThat(gridService.getAllGrids()).isEqualTo(grids);
		verify(gridRepository,times(1)).findAll();
			
	}
	@Test
	public void DropTableDeleteIsCalled() {
		DatabaseGrid grid1=new DatabaseGrid(1);
		given(gridRepository.delete(grid1)).willReturn(null);
		gridService.dropTable(grid1);
		verify(gridRepository,times(1)).delete(grid1);
		
	}
	

}
