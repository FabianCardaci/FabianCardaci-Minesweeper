package com.despegar.highflight.service;

public abstract class Cell {
	
	boolean showEnable;
	String content;
	boolean flag;
	
// Getters
//-------------------------------------------------------------------------------
	public boolean getShowEnable() {
		return showEnable;
	}
	
	public String getContent() {
		return content;
	}
	
	public boolean getShowEnabled() {
		return this.showEnable;
	}


//Others
//---------------------------------------------------------------------------------
	public void loadContent(MinesweeperImpl minesweeper){}
	
	public void uncover(MinesweeperImpl minesweeper) {}

	public String getText() {
		if (flag) {
			return "F";
		} else {
			if (showEnable) {
				return content;
			} else {
				return "-";
			}
		}
	}
	
	public void flagAsMine() {
		flag = true;
	}
	
	public void clearFlag() {
		flag = false;
	}
}