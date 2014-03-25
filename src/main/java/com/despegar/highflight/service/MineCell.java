package com.despegar.highflight.service;

public class MineCell implements Cell {
	
	boolean showEnable;
	
	public MineCell() {
		showEnable = false;
	}
	
	public boolean getShowEnable() {
		return showEnable;
	}

	public void loadContent(MinesweeperImpl minesweeper) {}
	
	public String getContent() {
		return "M";
	}
	
	
}
