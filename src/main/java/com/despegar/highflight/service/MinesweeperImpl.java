package com.despegar.highflight.service;

import java.util.Random;
import java.util.Set;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

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
	
	public void uncover(int row, int col) {
		if (cells[row][col].getContent() == "M") {
			cells[row][col].uncover();
		} else {
			MatrixUtils matrixUtil = new MatrixUtils();
			Set<Matrix2DCellPosition> positions = matrixUtil.cascade(getRawGrid(), row, col);
			for (Matrix2DCellPosition position : positions) {
				cells[position.getRow()][position.getColumn()].uncover();
			}
		}
	}
	
	public void flagAsMine(int row, int col){
		cells[row][col].flagAsMine();
	}
	
	public void clearFlag(int row, int col) {
		cells[row][col].clearFlag();
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
	
	public int gridSize() {
		return rows()*cols();
	}
	
	public int quantityOfMines() {
		int count = 0;
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (cells[i][j]. getContent() == "M") {
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
				if (cells[i][j].getContent()=="M" && cells[i][j].getShowEnable()) {
					exploited = true;
				}
			}
		}
		return exploited;
	}
	
	//DISPLAY
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
	
	public int[][] getRawGrid() {
		int[][] rawGrid = new int[rows()][cols()];
		for (int i=0; i<rows(); i++) {
			for (int j=0; j<cols(); j++) {
				if (cells[i][j].getContent() == "M") {
					rawGrid[i][j] = 1;
				} else {
					rawGrid[i][j] = 0;
				}
			}
		}
		return rawGrid;
	}

	
	//GETTERS AND SETTERS
	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

}
