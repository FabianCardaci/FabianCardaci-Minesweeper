package com.despegar.highflight.service;

import java.util.Random;
import java.util.Set;
import com.despegar.highflight.utils.*;

public class MinesweeperImpl implements Minesweeper {
	
	private Cell[][] cells;
	private int[][] rawGrid;
	private boolean winningGame;
	private boolean exploitedMine;
	private int uncoveredCells;
	private int quantityOfMines;
	
	
//Initialization and load
//-------------------------------------------------------------------------------------
	public MinesweeperImpl(int rows, int cols) {
		this.winningGame = false;
		this.exploitedMine = false;
		this.uncoveredCells = 0;
		this.quantityOfMines = (int) Math.rint(rows * cols * 0.15);
		cells = new Cell[rows][cols];
		loadMineCells();
		loadNumberCells();
		this.rawGrid = getRawGrid();
	}
	
	public void loadMineCells() {
		for (int i=1; i<=quantityOfMines; i++) {
			Matrix2DCellPosition cellPosition = randomCellPosition();
			cells[cellPosition.getRow()][cellPosition.getColumn()] = new MineCell();
		}
	}
	
	public Matrix2DCellPosition randomCellPosition() {
		Random random = new Random();
		int row = random.nextInt(rows());
		int col = random.nextInt(cols());
		if (cells[row][col] == null) {
			return new Matrix2DCellPosition(row,col);
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
					cells[i][j] = new NumberCell(new Matrix2DCellPosition(i,j));
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

	
//Others
//---------------------------------------------------------------------------------
	public int rows() {
		return cells.length;
	}
	
	public int cols() {
		return cells[0].length;
	}
	
	public Cell getCell(int row, int col) {
		return cells[row][col];
	}
	
	public void flagAsMine(int row, int col){
		cells[row][col].flagAsMine();
	}
	
	public void clearFlag(int row, int col) {
		cells[row][col].clearFlag();
	}
	
	public boolean thereIsMine(int row, int col) {
		return cells[row][col].getContent() == "M";
	}
	
	public int gridSize() {
		return rows()*cols();
	}
	
	public void exploitedMine() {
		this.exploitedMine = true;
	}
	
	public int quantityOfNumberCells() {
		return gridSize() - quantityOfMines;
	}
	
	public boolean isWinningGame() {
		return winningGame;
	}

	public boolean isGameOver() {
		return (winningGame || exploitedMine);
	}

	public void uncover(int row, int col) {
		if (thereIsMine(row,col)) {
			cells[row][col].uncover(this);
		} else {
			Set<Matrix2DCellPosition> positions = MatrixUtils.cascade(rawGrid, row, col);
			for (Matrix2DCellPosition position : positions) {
				cells[position.getRow()][position.getColumn()].uncover(this);
			}
		}
	}
	
	public int[][] getRawGrid() {
		int[][] rawGrid = new int[rows()][cols()];
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (thereIsMine(i,j)) {
					rawGrid[i][j] = 1;
				} else {
					rawGrid[i][j] = 0;
				}
			}
		}
		return rawGrid;
	}
	
	public void increaseUncoveredCells() {
		uncoveredCells++;
		if (uncoveredCells == quantityOfNumberCells()) {
			winningGame = true;
		}
	}
	
	
//Display
//----------------------------------------------------------------------------------
	public void display(){
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				System.out.print(cells[i][j].getText() + "\t");
			}
			System.out.println("\n");
		}
	}
	
	public void displayInternal() {
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				System.out.print(cells[i][j].getContent() + "\t");
			}
			System.out.println("\n");
		}
	}
	
	public void displayRaw(){
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				System.out.print(rawGrid[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	

//Getters and setters
//----------------------------------------------------------------------------------
	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
}
