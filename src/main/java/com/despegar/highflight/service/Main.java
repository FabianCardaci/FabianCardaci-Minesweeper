package com.despegar.highflight.service;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		MinesweeperImpl game = new MinesweeperImpl(4,5);
		Scanner teclado = new Scanner(System.in);
		String action;
		int row;
		int col;
		System.out.println("MINESWEEPER" + '\n');
		game.display();
		System.out.println("\n");
		while (!game.isGameOver()) {
			do {
				System.out.print("Choose an action [ u:Uncover - f:Flag - c:ClearFlag ] : ");
				action = teclado.next();
			} while(!action.equals("u") && !action.equals("f") && !action.equals("c"));
			System.out.print("\n");
			System.out.println("Put a cell position :");
			System.out.print(" Row = ");
			do {
				row = teclado.nextInt();
			} while(row<1 || row>game.rows());
			System.out.print(" Col = ");
			do {
				col = teclado.nextInt();
			} while(col<1 || col>game.cols());
			switch (action.charAt(0)) {
			case 'u': game.uncover(row-1, col-1); break;
			case 'f': game.flagAsMine(row-1, col-1); break;
			case 'c': game.clearFlag(row-1, col-1); break;
			}
			System.out.println("\n");
			game.display();
			System.out.println("\n");
			
		}
		
		if (game.isWinningGame()) {
			System.out.println("YOU WIN!");
		} else {
			game.displayInternal();
			System.out.println("YOU LOSE!");
		}

	}

}
