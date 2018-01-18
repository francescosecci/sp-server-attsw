package com.pufose.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MysqlImplementorTest {
	@Mock
	private MysqlRepository gridRepository;
	
	@InjectMocks
	private MySqlImplementor  implementor;
	
	@Test
	public void getAllIdWhenThereIsOneGridTest() {
		
		DatabaseGrid grid1 = new DatabaseGrid(1);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1));
		assertThat(implementor.getAllId()).isEqualTo(Arrays.asList("1"));
		verify(gridRepository, times(1)).findAll();
		
	}
	
	@Test
	public void getAllIdWhenThereIsNoGridTest() {
		
		given(gridRepository.findAll()).willReturn(Arrays.asList());
		assertThat(implementor.getAllId()).isEqualTo(new ArrayList<>());
		verify(gridRepository, times(1)).findAll();

	}
	
	@Test
	public void getAllIdWhenThereIsMoreThanOneGridTest() {
		DatabaseGrid grid1 = new DatabaseGrid(1);
		DatabaseGrid grid2 = new DatabaseGrid(2);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1, grid2));
		assertThat(implementor.getAllId()).isEqualTo(Arrays.asList("1", "2"));
		verify(gridRepository, times(1)).findAll();
	}

}
