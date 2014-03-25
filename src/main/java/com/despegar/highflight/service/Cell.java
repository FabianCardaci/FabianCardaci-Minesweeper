package com.despegar.highflight.service;

public interface Cell {

	boolean getShowEnable();
	
	void loadContent(MinesweeperImpl minesweeper);
	
	String getContent();
	
}
