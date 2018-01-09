package com.pufose.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	@Autowired
	private IGridRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		
	}
	@Override
	public void run(String...args) throws Exception {
		int[][] mat1=new int[][] {
			{1,0,1,1,0},
			{1,1,1,1,1},
			{1,1,1,0,1},
			{1,0,1,1,0},
			{1,1,1,1,1}
		};
		int[][] mat2=new int[][] {
			{1,1,1,1},
			{1,0,1,0},
			{1,0,1,0},
			{1,1,1,1}
		};
		int[][] mat3=new int[][] {
			{1,1,1,1,1,1,1},
			{1,1,1,1,0,0,1},
			{1,1,1,0,0,0,1},
			{1,1,1,1,0,0,1},
			{1,1,1,1,1,1,1},
			{1,0,0,0,1,1,1},
			{1,1,1,1,0,0,1},
		};
		int[][] mat4=new int[][] {
			{1,1,1},
			{1,0,0},
			{1,1,0}
		};
		int[][]mat5=new int[][] {
			{1,0,0,0},
			{1,1,1,0},
			{1,0,0,0},
			{0,0,1,0}
		};
		repo.save(new DatabaseGrid(mat1,1));
		repo.save(new DatabaseGrid(mat2,2));
		repo.save(new DatabaseGrid(mat3,3));
		repo.save(new DatabaseGrid(mat4,4));
		repo.save(new DatabaseGrid(mat5,5));
	}
}
