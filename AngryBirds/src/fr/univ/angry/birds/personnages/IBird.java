package fr.univ.angry.birds.personnages;

import java.awt.Graphics;

public interface IBird {
	
	/**
	 * Buid a graphical bird 
	 * @param graphic the actual graphic where you want build the bird
	 * @param startPosX start position X of the bird
	 * @param StartPosY start position Y of the bird
	 * @return the graphics with you bird
	 */
	public Graphics build(Graphics graphic, int startPosX, int StartPosY);
	
	public double getFloorValue();
}
