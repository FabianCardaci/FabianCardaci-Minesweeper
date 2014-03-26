package com.despegar.highflight.service;

public class MineCell extends Cell {
	
	public MineCell() {
		content = "M";
		showEnable = false;
		flag = false;
	}
	
	
	@Override
	public void uncover(MinesweeperImpl minesweeper) {
		if (!showEnable) {
			this.showEnable = true;
			minesweeper.exploitedMine();
		}
	}
	
}
