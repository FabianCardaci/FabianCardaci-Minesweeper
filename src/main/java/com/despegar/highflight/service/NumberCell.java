package com.despegar.highflight.service;

import java.util.HashSet;
import java.util.Iterator;
import com.despegar.highflight.utils.Matrix2DCellPosition;

public class NumberCell extends Cell {
	
	Matrix2DCellPosition cellPosition;
	
	public NumberCell(Matrix2DCellPosition cellPosition) {
		this.showEnable = false;
		this.flag=false;
		this.cellPosition = cellPosition;
	}
	
	public void loadContent(MinesweeperImpl minesweeper) {
		HashSet<Matrix2DCellPosition> adjacents = adjacentCellPositions(minesweeper);
		this.content = String.valueOf(adjacentMines(adjacents, minesweeper));
	}
	
	public int adjacentMines(HashSet<Matrix2DCellPosition> adjacents, MinesweeperImpl minesweeper) {
		int counter = 0;
		for( Matrix2DCellPosition cp : adjacents) {
    	    if (minesweeper.thereIsMine(cp.getRow(), cp.getColumn())) {
    	    	counter++;
    	    }
		}
		return counter;
	}
	
	public HashSet<Matrix2DCellPosition> adjacentCellPositions(MinesweeperImpl minesweeper) {
		HashSet<Matrix2DCellPosition> adjacents = new HashSet<Matrix2DCellPosition>();
		for( Matrix2DCellPosition cp : getAllAdjacents() ) { 
    	    if (isCorrectPosition(cp, minesweeper)) {
    	    	adjacents.add(cp);
    	    }
	    }
		return adjacents;
	}
	
	public boolean isCorrectPosition(Matrix2DCellPosition cp, MinesweeperImpl minesweeper) {
		return ( (cp.getRow()>=0) && 
				 (cp.getColumn()>=0) && 
				 (cp.getRow()<minesweeper.rows()) && 
				 (cp.getColumn()<minesweeper.cols()));
	}
	
	public HashSet<Matrix2DCellPosition> getAllAdjacents() {
		HashSet<Matrix2DCellPosition> allAdjacents = new HashSet<Matrix2DCellPosition>();
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow()-1,cellPosition.getColumn()-1));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow()-1,cellPosition.getColumn()));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow()-1,cellPosition.getColumn()+1));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow(),cellPosition.getColumn()-1));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow(),cellPosition.getColumn()+1));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow()+1,cellPosition.getColumn()-1));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow()+1,cellPosition.getColumn()));
		allAdjacents.add(new Matrix2DCellPosition(cellPosition.getRow()+1,cellPosition.getColumn()+1));
		return allAdjacents;
	}
	
	@Override
	public void uncover(MinesweeperImpl minesweeper) {
		if (!showEnable) {
			this.showEnable = true;
			minesweeper.increaseUncoveredCells();
		}
	}
}