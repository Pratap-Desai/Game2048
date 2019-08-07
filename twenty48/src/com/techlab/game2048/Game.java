package com.techlab.game2048;

import apcs.Window;

public class Game {

	public static String colors[] = { "Red", "Brown", "Blue", "Green", "White" };

	public static void main(String[] args) {
		Window.size(600, 600);
		GameMatrix gameMatrix = new GameMatrix();
		gameMatrix.generate2();
		gameMatrix.generate2();

		while (true) {
			if (gameMatrix.gameOver()) {
				System.out.println("Game Over");
				while (!Window.key.pressed('r')) {
					endGame();
				}
				gameMatrix.restart();
			}
			gameMatrix.move();
			drawGame(gameMatrix.getMatrix());
			Window.frame(120);
		}
	}

	private static void endGame() {
		Window.out.background("black");
		Window.out.color("Red");
		Window.out.font("Kameron", 50);
		Window.out.print("Game Over", Window.width() / 4, Window.height() / 2);
		Window.out.color("Green");
		Window.out.font("Kameron", 30);
		Window.out.print("Press 'r' to Restart game", Window.width() / 4, 3 * Window.height() / 4);
		Window.frame();

	}

	public static void drawGame(int[][] matrix) {
		int startpt = 75;
		int sideLen = 120;
		int sideLen2 = 50;
		int gap = 150;

		Window.out.background("orange");
		int m = 0;
		int n = 0;
		for (int i = startpt; n < 4; i += gap) {
			m = 0;
			for (int j = startpt; m < 4; j += gap) {
				Window.out.color("yellow");
				Window.out.square(i, j, sideLen);
				if (matrix[m][n] != 0) {
					int r = (int) (Math.log(matrix[m][n]) / Math.log(2));
					Window.out.color(colors[r % colors.length]);
					Window.out.square(i, j, sideLen2);
					Window.out.color("Black");
					Window.out.font("Kameron", 50);
					Window.out.print(matrix[m][n], i - 15, j + 15);
				} else {
					Window.out.color("grey");
					Window.out.square(i, j, sideLen2);

				}
				m++;
			}

			n++;
		}
	}

}
