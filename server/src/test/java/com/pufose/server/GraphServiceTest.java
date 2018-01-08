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
	public void GetAllNamesWhenThereIsMoreThanOneGrid() {
		DatabaseGrid grid1=new DatabaseGrid(0);
		DatabaseGrid grid2=new DatabaseGrid(1);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1,grid2));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("0","1"));
		verify(gridRepository,times(1)).findAll();
	}

}
