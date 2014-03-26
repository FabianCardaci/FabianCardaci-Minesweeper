package com.despegar.highflight.service;

import java.util.Random;
import java.util.Set;
import com.despegar.highflight.utils.*;

public class MinesweeperImpl implements Minesweeper {
	
	private Cell[][] cells;
	
	//Initialization and load
	public MinesweeperImpl(int rows, int cols) {
		cells = new Cell[rows][cols];
		loadMineCells(0.15);
		loadNumberCells();
	}
	
	public void loadMineCells(double percent) {
		int quantityOfMines = (int) Math.rint(rows() * cols() * percent);
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
		return cells[row][col].getContent().equals("M");
	}
	
	public int gridSize() {
		return rows()*cols();
	}

	public void uncover(int row, int col) {
		if (thereIsMine(row,col)) {
			cells[row][col].uncover();
		} else {
			Set<Matrix2DCellPosition> positions = MatrixUtils.cascade(getRawGrid(), row, col);
			for (Matrix2DCellPosition position : positions) {
				cells[position.getRow()][position.getColumn()].uncover();
			}
		}
	}
	
	public boolean isWinningGame() {
		int showCells = 0;
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (cells[i][j].getShowEnable()) {
					showCells++;
				}
			}
		}
		if (showCells < (gridSize() - quantityOfMines())) {
			return false;
		} else {
			return true;
		}
	}
	
	public int quantityOfMines() {
		int count = 0;
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (thereIsMine(i,j)) {
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean isGameOver() {
		return (isWinningGame() || exploitedMine());
	}
	
	public boolean exploitedMine() {
		boolean exploited = false;
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (thereIsMine(i,j) && cells[i][j].getShowEnable()) {
					exploited = true;
				}
			}
		}
		return exploited;
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

	
	//Display
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
		int[][] rawGrid = getRawGrid();
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				System.out.print(rawGrid[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	

	//Getters and setters
	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

}
