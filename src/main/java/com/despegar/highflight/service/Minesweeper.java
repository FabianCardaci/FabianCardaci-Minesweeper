package com.despegar.highflight.service;

public interface Minesweeper {

	// Allow the player to uncover a cell
	void uncover(int row, int col);
	
	// Marking/unmarking suspicious cells
	void flagAsMine(int row, int col);
	void clearFlag(int row, int col);
	
	// Game termination
	boolean isGameOver();
	boolean isWinningGame();
	
	// Operations for showing the current state of game grid
	// Public/visible grid for the player
	void display();
	// Grid with all cells uncovered. For debug purposes
	void displayInternal();
	// Binary grid: 1 if cell has a mine, 0 otherwise. For debug purposes
	void displayRaw();

}
