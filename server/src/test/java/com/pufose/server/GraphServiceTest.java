package com.pufose.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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

	@Mock
	private Graph tobuild;

	@Test
	public void getAllIdWhenThereIsMoreThanOneGridTest() {
		DatabaseGrid grid1 = new DatabaseGrid(1);
		DatabaseGrid grid2 = new DatabaseGrid(2);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1, grid2));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("1", "2"));
		verify(gridRepository, times(1)).findAll();
	}

	@Test
	public void getAllIdWhenThereIsOneGridTest() {
		DatabaseGrid grid1 = new DatabaseGrid(1);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1));
		assertThat(gridService.getAllId()).isEqualTo(Arrays.asList("1"));
		verify(gridRepository, times(1)).findAll();

	}

	@Test
	public void getAllIdWhenThereIsNoGridTest() {
		List<DatabaseGrid> grids = new ArrayList<>();
		given(gridRepository.findAll()).willReturn(Arrays.asList());
		assertThat(gridService.getAllId()).isEqualTo(grids);
		verify(gridRepository, times(1)).findAll();

	}

	@Test
	public void getByIdWhenThereIsNoSuchGridTest() {
		given(gridRepository.findById(7)).willReturn(null);
		assertThat(gridService.getById(7)).isEqualTo(null);
		verify(gridRepository, times(1)).findById(7);

	}

	@Test
	public void getByIdWhenThereIsSuchGridTest() {
		DatabaseGrid grid1 = new DatabaseGrid(1);
		given(gridRepository.findById(1)).willReturn(grid1);
		assertThat(gridService.getById(1)).isEqualTo(grid1);
		verify(gridRepository, times(1)).findById(1);

	}

	@Test
	public void saveInDbIsCalledTest() {
		DatabaseGrid grid1 = new DatabaseGrid(1);
		given(gridRepository.save(grid1)).willReturn(null);
		gridService.storeInDb(grid1);
		verify(gridRepository, times(1)).save(grid1);

	}

	@Test
	public void nextIdWhenThereIsNoGridTest() {
		given(gridRepository.findAll()).willReturn(new ArrayList<>());
		assertThat(gridService.nextId()).isEqualTo(1);
		verify(gridRepository, times(1)).findAll();

	}

	@Test
	public void nextIdWhenThereAreGridsTest() {
		DatabaseGrid grid1 = new DatabaseGrid(1);
		DatabaseGrid grid2 = new DatabaseGrid(2);
		given(gridRepository.findAll()).willReturn(Arrays.asList(grid1, grid2));
		assertThat(gridService.nextId()).isEqualTo(3);
		verify(gridRepository, times(1)).findAll();
	}

	@Test
	public void getAllGridsWhenThereIsNoGridsTest() {
		List<DatabaseGrid> grids = new ArrayList<>();
		given(gridRepository.findAll()).willReturn(new ArrayList<>());
		assertThat(gridService.getAllGrids()).isEqualTo(grids);
		verify(gridRepository, times(1)).findAll();

	}

	@Test
	public void dropTableDeleteIsCalledTest() {
		
		gridService.dropTable(1);
		verify(gridRepository, times(1)).delete(1);

	}

	@Test
	public void getMinPathTest() {
		int[][] mat = new int[][] { { 1, 1 }, { 0, 1 }, };
		given(gridRepository.findById(1)).willReturn(new DatabaseGrid(mat, 1));
		given(tobuild.minPath("0_0", "1_1")).willReturn(Arrays.asList("0_0", "0_1", "1_1"));
		given(tobuild.getNodes()).willReturn(Arrays.asList("0_0", "0_1", "1_1"));
		List<String> expectedMinPath = Arrays.asList("0_0", "0_1", "1_1");
		assertEquals(gridService.getShortestPath("0_0", "1_1", 1), expectedMinPath);
		verify(gridRepository, times(1)).findById(1);
		verify(tobuild, times(1)).minPath("0_0", "1_1");

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] == 1) {

					verify(tobuild, times(1)).addNodes(i + "_" + j);
				}
			}
		}

		verify(tobuild, times(1)).addEdge("0_0", "0_1");
		verify(tobuild, times(1)).addEdge("0_1", "1_1");

	}

}
