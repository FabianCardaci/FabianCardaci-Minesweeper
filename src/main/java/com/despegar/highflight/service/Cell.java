package com.despegar.highflight.service;

public abstract class Cell {
	
	boolean showEnable;
	String content;
	boolean flag;
	
	public boolean getShowEnable() {
		return showEnable;
	}
	
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
	
	public String getContent() {
		return content;
	}
	
	
	public void loadContent(MinesweeperImpl minesweeper){}
	
	public void uncover() {
		showEnable = true;
	
	}
	
	public void flagAsMine() {
		flag = true;
	}
	
	public void clearFlag() {
		flag = false;
	}
	
	public boolean getShowEnabled() {
		return this.showEnable;
	}
	
}
