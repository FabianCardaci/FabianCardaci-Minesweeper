package com.despegar.highflight.service;

import java.util.HashSet;
import java.util.Iterator;

public class NumberCell implements Cell {
	
	CellPosition cellPosition;
	int content;
	boolean showEnable;
	
	public NumberCell(CellPosition cellPosition) {
		this.showEnable = true;
		this.cellPosition = cellPosition;
	}
	
	public boolean getShowEnable() {
		return showEnable;
	}
	
	public String getContent() {
		return String.valueOf(content);
	}
	
	public void loadContent(MinesweeperImpl minesweeper) {
		HashSet<CellPosition> adjacents = adjacentCellPositions(minesweeper);
		this.content = adjacentMines(adjacents, minesweeper);
	}
	
	public HashSet<CellPosition> adjacentCellPositions(MinesweeperImpl minesweeper) {
		HashSet<CellPosition> adjacentsAux = new HashSet<CellPosition>();
		HashSet<CellPosition> adjacents = new HashSet<CellPosition>();
		adjacentsAux.add(new CellPosition(cellPosition.row-1,cellPosition.col-1));
		adjacentsAux.add(new CellPosition(cellPosition.row-1,cellPosition.col));
		adjacentsAux.add(new CellPosition(cellPosition.row-1,cellPosition.col+1));
		adjacentsAux.add(new CellPosition(cellPosition.row,cellPosition.col-1));
		adjacentsAux.add(new CellPosition(cellPosition.row,cellPosition.col+1));
		adjacentsAux.add(new CellPosition(cellPosition.row+1,cellPosition.col-1));
		adjacentsAux.add(new CellPosition(cellPosition.row+1,cellPosition.col));
		adjacentsAux.add(new CellPosition(cellPosition.row+1,cellPosition.col+1));

		for( Iterator<CellPosition> iterator = adjacentsAux.iterator(); iterator.hasNext();) { 
    	    CellPosition cp = (CellPosition) iterator.next();
    	    if ((cp.row>=0) && (cp.col>=0) && (cp.row<minesweeper.rows()) && (cp.col<minesweeper.cols())) {
    	    	adjacents.add(cp);
    	    }
	    }
		return adjacents;
	}
	
	public int adjacentMines(HashSet<CellPosition> adjacents, MinesweeperImpl minesweeper) {
		int counter = 0;
		for( Iterator<CellPosition> iterator = adjacents.iterator(); iterator.hasNext();) {
    	    CellPosition cp = iterator.next();
    	    if (minesweeper.getCell(cp.row, cp.col).getContent() == "M") {
    	    	counter++;
    	    }
		}
		return counter;
	}

}