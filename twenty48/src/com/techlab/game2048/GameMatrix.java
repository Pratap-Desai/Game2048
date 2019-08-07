package com.techlab.game2048;

import java.util.Random;

import apcs.Window;

public class GameMatrix {

	private int[][] matrix;
	private int size;
	private Random rand;
	int upkeycount = 0;
	int downkeycount = 0;
	int leftcount = 0;
	int rightcount = 0;
	
	public GameMatrix() {

		size = 4;
		matrix = new int[size][size];
		rand = new Random();

	}
	/*
	 * 
	 * 0 up 1 down 2 left 3 right
	 */

	public boolean gameOver() {
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (matrix[i][j] == 0 || matrix[i][j + 1] == 0 || matrix[i + 1][j] == 0
						|| matrix[i][j] == matrix[i][j + 1] || matrix[i][j] == matrix[i + 1][j]) {
					return false;
				}
			}
		}
		for (int i = 0; i < size - 1; i++) {
			if (matrix[i][size - 1] == 0 || matrix[i][size - 1] == matrix[i + 1][size - 1] || matrix[size - 1][i] == 0
					|| matrix[size - 1][i] == matrix[size - 1][i + 1]) {
				return true;
			}
		}

		return true;
	}

	public void generate2() {
		int count = 0;
		int i = rand.nextInt(size);
		while (!checkIfRowPossible(i) && count < 10) {
			i = rand.nextInt(size);
			count++;
		}
		int j = rand.nextInt(size);
		while (matrix[i][j] != 0 && count < 10) {
			j = rand.nextInt(size);

		}
		if (count < 10)
			matrix[i][j] = 2;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	private boolean checkIfRowPossible(int i) {
		for (int j = 0; j < size; j++) {
			if (matrix[i][j] == 0) {
				return true;
			}
		}
		return false;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void move() {

		if (Window.key.pressed("up")) {

			boolean works = false;
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < size - 1; i++) {
					int n = i + 1;
					if (matrix[i][j] == matrix[i + 1][j] && matrix[i][j] != 0) {
						works = true;
						matrix[i][j] += matrix[i + 1][j];
						for (int k = i + 1; k < size - 1; k++) {
							matrix[k][j] = matrix[k + 1][j];
						}
						matrix[size - 1][j] = 0;
					} else if (matrix[i][j] != 0 && matrix[i + 1][j] == 0) {
						while (n + 1 < size && matrix[i][j] != 0 && matrix[n][j] == 0) {
							if (matrix[n + 1][j] == matrix[i][j]) {
								works = true;
								matrix[i][j] += matrix[n + 1][j];
								matrix[n + 1][j] = 0;
							}
							n++;
						}
					}
				}
			}
			for (int j = 0; j < size; j++) {
				for (int i = size - 1; i > 0; i--) {
					if (matrix[i - 1][j] == 0 && matrix[i][j] != 0) {
						works = true;
						matrix[i - 1][j] += matrix[i][j];
						matrix[i][j] = 0;
					}
				}
			}
			System.out.println("up=  " + upkeycount);
			upkeycount++;
			print();
			if (works) {
				generate2();
			}

		} else if (Window.key.pressed("down")) {
			boolean works = false;
			for (int j = 0; j < size; j++) {
				for (int i = size - 1; i > 0; i--) {
					int n = i - 1;
					if (matrix[i][j] == matrix[i - 1][j] && matrix[i][j] != 0) {
						works = true;
						matrix[i][j] += matrix[i - 1][j];
						for (int k = i - 1; k > 0; k--) {
							matrix[k][j] = matrix[k - 1][j];
						}
						matrix[0][j] = 0;
					} else if (matrix[i][j] != 0 && matrix[i - 1][j] == 0) {
						while (n - 1 >= 0 && matrix[i][j] != 0 && matrix[n][j] == 0) {
							if (matrix[n - 1][j] == matrix[i][j]) {
								works = true;
								matrix[i][j] += matrix[n - 1][j];
								matrix[n - 1][j] = 0;
							}
							n--;
						}
					}
				}
			}
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < size - 1; i++) {
					if (matrix[i + 1][j] == 0 && matrix[i][j] != 0) {
						matrix[i + 1][j] += matrix[i][j];
						works = true;
						matrix[i][j] = 0;
					}
				}
			}
			System.out.println("down=  " + downkeycount);
			downkeycount++;
			print();
			if (works) {
				generate2();	
			}
			
		}

		else if (Window.key.pressed("left")) {
			boolean works=false;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size - 1; j++) {
					int n = j + 1;
					if (matrix[i][j] == matrix[i][j + 1] && matrix[i][j] != 0) {
						works=true;
						matrix[i][j] += matrix[i][j + 1];
						for (int k = j + 1; k < size - 1; k++) {
							matrix[i][k] = matrix[i][k + 1];
						}
						matrix[i][size - 1] = 0;
					} else if (matrix[i][j] != 0 && matrix[i][j + 1] == 0) {
						while (n + 1 < size && matrix[i][j] != 0 && matrix[i][n] == 0) {
							if (matrix[i][n + 1] == matrix[i][j]) {
								works=true;
								matrix[i][j] += matrix[i][n + 1];
								matrix[i][n + 1] = 0;
							}
							n++;
						}
					}
				}
			}
			for (int i = 0; i < size; i++) {
				for (int j = size - 1; j > 0; j--) {
					if (matrix[i][j - 1] == 0 && matrix[i][j] != 0) {
						works = true;
						matrix[i][j - 1] += matrix[i][j];
						matrix[i][j] = 0;
					}
				}
			}
			System.out.println("left=  " + leftcount);
			leftcount++;
			print();
			if (works) {
				generate2();
			}
			
		} else if (Window.key.pressed("right")) {
			boolean works=false;
			for (int i = 0; i < size; i++) {
				for (int j = size - 1; j > 0; j--) {
					int n = j - 1;
					if (matrix[i][j] == matrix[i][j - 1] && matrix[i][j] != 0) {
						works=true;
						matrix[i][j] += matrix[i][j - 1];
						for (int k = j - 1; k > 0; k--) {
							matrix[i][k] = matrix[i][k - 1];
						}
						matrix[i][0] = 0;
					} else if (matrix[i][j] != 0 && matrix[i][j - 1] == 0) {
						while (n - 1 >= 0 && matrix[i][j] != 0 && matrix[i][n] == 0) {
							if (matrix[i][n - 1] == matrix[i][j]) {
								works=true;
								matrix[i][j] += matrix[i][n - 1];
								matrix[i][n - 1] = 0;
							}
							n--;
						}
					}
				}
			}
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size - 1; j++) {
					if (matrix[i][j + 1] == 0 && matrix[i][j] != 0) {
						works = true;
						matrix[i][j + 1] += matrix[i][j];
						matrix[i][j] = 0;
					}
				}
			}
			System.out.println("right=  " + rightcount);
			rightcount++;
			print();
			if (works) {
				generate2();	
			}
			
		}
	}

	public void restart() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = 0;
			}
		}
		generate2();
		generate2();
	}
}
