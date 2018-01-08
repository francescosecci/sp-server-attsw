package com.pufose.server;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;
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
		DatabaseGrid grid1=new DatabaseGrid(0);
		DatabaseGrid grid2=new DatabaseGrid(1);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1,grid2));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("0","1"));
		verify(gridRepository,times(1)).findAll();
	}
	
	@Test
	public void GetAllIdWhenThereIsOneGrid() {
		DatabaseGrid grid1=new DatabaseGrid(0);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("0"));
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
	

}
