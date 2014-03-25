package com.despegar.highflight.service;

import java.util.Random;

public class MinesweeperImpl implements Minesweeper {
	
	private Cell[][] cells;
	
	//INITIALIZATION AND LOAD
	public MinesweeperImpl(int rows, int cols) {
		cells = new Cell[rows][cols];
		loadMineCells(0.15);
		loadNumberCells();
	}
	
	public void loadMineCells(double percent) {
		int quantityOfMines = (int) Math.rint(rows() * cols() * percent);
		for (int i=1; i<=quantityOfMines; i++) {
			CellPosition cellPosition = randomCellPosition();
			cells[cellPosition.row][cellPosition.col] = new MineCell();
		}
	}
	
	public CellPosition randomCellPosition() {
		Random random = new Random();
		int row = random.nextInt(rows());
		int col = random.nextInt(cols());
		if (cells[row][col] == null) {
			return new CellPosition(row,col);
		} else {
			return randomCellPosition();
		}
	}
	
	public void loadNumberCells() {
		loadCells();
		loadNumbers();
	}
	
	public void loadCells() {
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (cells[i][j] == null){
					cells[i][j] = new NumberCell(new CellPosition(i,j));
				}
			}
		}
	}
	
	public void loadNumbers() {
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				cells[i][j].loadContent(this);
			}
		}
	}
	
	//OTROS
	public int rows() {
		return cells.length;
	}
	
	public int cols() {
		return cells[0].length;
	}
	
	public Cell getCell(int row, int col) {
		return cells[row][col];
	}
	
	// Allow the player to uncover a cell
	public void uncover(int row, int col){}
	
	// Marking/unmarking suspicious cells
	public void flagAsMine(int row, int col){}
	public void clearFlag(int row, int col){}
	
	// Game termination
	public boolean isGameOver(){return true;}
	public boolean isWinningGame(){return true;}
	
	public void display(){
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (cells[i][j].getShowEnable()) {
					System.out.print(showCellContent(i, j) + "\t");
				}
			}
			System.out.println("\n");
		}
	}
	
	public void displayInternal() {
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				System.out.print(showCellContent(i, j) + "\t");
			}
			System.out.println("\n");
		}
	}
	
	public void displayRaw(){
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (cells[i][j].getContent() == "M") {
					System.out.print(1 + "\t");
				} else {
					System.out.print(0 + "\t");
				}
			}
			System.out.println("\n");
		}	
	}

	
	

	//GETTERS AND SETTERS
	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	

	//MAIN
	public String showCellContent(int row, int col) {
		return String.valueOf(cells[row][col].getContent());
	}

	public static void main(String[] args) {
		MinesweeperImpl ms = new MinesweeperImpl(4,5);
		System.out.println("DISPLAY INTERNAL");
		ms.displayInternal();
		System.out.println("\n");
		System.out.println("DISPLAY");
		ms.display();
		System.out.println("\n");
		System.out.println("DISPLAY RAW");
		ms.displayRaw();
	}

}
