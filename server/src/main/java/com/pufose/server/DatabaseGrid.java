package com.pufose.server;

public class DatabaseGrid {

	private int id;
	private int[][] mat;

	public int getId() {
		return id;
	}

	public DatabaseGrid(int[][] mat, int id) {
		super();
		this.id = id;
		this.mat = mat;
	}

	public DatabaseGrid(int id) {
		super();
		this.id = id;
		this.mat = new int[0][0];
	}

	public int getN() {

		return mat.length;
	}

	public boolean isEnabled(int i, int j) {

		return mat[i][j] > 0;
	}

	public String getName(int i, int j) {
		// TODO Auto-generated method stub
		return i + "_"+ j;
	}

}
